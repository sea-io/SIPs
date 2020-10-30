func benchInsertChain(b *testing.B, disk bool, gen func(int, *BlockGen)) {
	// Create the database in memory or in a temporary directory.
	var db ethdb.Database
	if !disk {
		db, _ = ethdb.NewMemDatabase()
		
	} else {
		dir, err := ioutil.TempDir("", "eth-core-bench")
		if err != nil {
			b.Fatalf("cannot create temporary directory: %v", err)
		}
		defer os.RemoveAll(dir)
		db, err = ethdb.NewLDBDatabase(dir, 128, 128)
		if err != nil {
			b.Fatalf("cannot create temporary database: %v", err)
		}
		defer db.Close()
	}

	// Generate a chain of b.N blocks using the supplied block
	// generator function.
	genesis := WriteGenesisBlockForTesting(db, GenesisAccount{benchRootAddr, benchRootFunds})
	chain, _ := GenerateChain(DefaultConfigMainnet.ChainConfig, genesis, db, b.N, gen)

	// Time the insertion of the new chain.
	// State and blocks are stored in the same DB.
	evmux := new(event.TypeMux)
	chainman, _ := NewBlockChain(db, DefaultConfigMainnet.ChainConfig, FakePow{}, evmux)
	defer chainman.Stop()
	b.ReportAllocs()
	b.ResetTimer()


	if res := chainman.InsertChain(chain); res.Error != nil {
		b.Fatalf("insert error (block %d): %v\n", res.Index, res.Error)
	}
