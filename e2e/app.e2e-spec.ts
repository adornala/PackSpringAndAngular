import { Webpack1Page } from './app.po';

describe('webpack1 App', () => {
  let page: Webpack1Page;

  beforeEach(() => {
    page = new Webpack1Page();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
