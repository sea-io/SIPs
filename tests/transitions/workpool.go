
package workpool

import (
	"context"
	"golang.org/x/sync/semaphore"
	"sync"
)

type WorkerPool struct {
	maxWorkers     int64
	sem            *semaphore.Weighted
	tasks          map[string]Task
	ids            []string
	taskNumber     int64
	completeNumber int64
	sync.Mutex
}

func NewWorkPool(max int64) *WorkerPool {
	m := semaphore.NewWeighted(max)
	ts := make(map[string]Task)
	ids := make([]string, 0)
	return &WorkerPool{
		maxWorkers: max,
		sem:        m,
		tasks:      ts,
		ids:        ids,
	}
}

func (wp *WorkerPool) AddTask(task Task) {
	wp.taskNumber++
	wp.ids = append(wp.ids, task.UUID())
	wp.tasks[task.UUID()] = task
}

func (wp *WorkerPool) Top() Task {
	if len(wp.ids) == 0 {
		return nil
	}

	id := wp.ids[0]
	t := wp.tasks[id]

	delete(wp.tasks, id)
	wp.ids = wp.ids[1:]
	return t

}

func (wp *WorkerPool) Empty() bool {
	return len(wp.ids) == 0
}

func (wp *WorkerPool) Poll(ctx context.Context, quit chan int) {
	for !wp.Empty() {
		t := wp.Top()
		if err := wp.sem.Acquire(ctx, 1); err != nil {
			break
		}
		go func() {
			defer wp.sem.Release(1)
			t.Run()

			wp.Mutex.Lock()
			wp.completeNumber++
			if wp.completeNumber == wp.taskNumber {
				quit <- 0
			}
			wp.Mutex.Unlock()
		}()
	}
}
