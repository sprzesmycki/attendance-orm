import { AttendanceUiPage } from './app.po';

describe('attendance-ui App', () => {
  let page: AttendanceUiPage;

  beforeEach(() => {
    page = new AttendanceUiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
