import { expect } from 'chai';
import { get } from 'lodash';
import { Given, When, Then } from 'cucumber';
import moment from 'moment';

import newsDummyJson from '../documents/dummy-news.json';
import {
  expectTextInSelector,
  getVisibleElementsCountForSelector,
} from '../../../common/e2e/steps/helpers';

async function prepareFakeNews(context, fakeNews, preparation, ...args) {}
