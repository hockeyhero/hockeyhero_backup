import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { HeroKeysComponentsPage, HeroKeysDeleteDialog, HeroKeysUpdatePage } from './hero-keys.page-object';

const expect = chai.expect;

describe('HeroKeys e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let heroKeysComponentsPage: HeroKeysComponentsPage;
  let heroKeysUpdatePage: HeroKeysUpdatePage;
  let heroKeysDeleteDialog: HeroKeysDeleteDialog;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing(username, password);
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load HeroKeys', async () => {
    await navBarPage.goToEntity('hero-keys');
    heroKeysComponentsPage = new HeroKeysComponentsPage();
    await browser.wait(ec.visibilityOf(heroKeysComponentsPage.title), 5000);
    expect(await heroKeysComponentsPage.getTitle()).to.eq('hockeyheroApp.heroKeys.home.title');
    await browser.wait(ec.or(ec.visibilityOf(heroKeysComponentsPage.entities), ec.visibilityOf(heroKeysComponentsPage.noResult)), 1000);
  });

  it('should load create HeroKeys page', async () => {
    await heroKeysComponentsPage.clickOnCreateButton();
    heroKeysUpdatePage = new HeroKeysUpdatePage();
    expect(await heroKeysUpdatePage.getPageTitle()).to.eq('hockeyheroApp.heroKeys.home.createOrEditLabel');
    await heroKeysUpdatePage.cancel();
  });

  it('should create and save HeroKeys', async () => {
    const nbButtonsBeforeCreate = await heroKeysComponentsPage.countDeleteButtons();

    await heroKeysComponentsPage.clickOnCreateButton();

    await promise.all([
      heroKeysUpdatePage.getHideMeInput().click(),
      heroKeysUpdatePage.setLatitudeInput('5'),
      heroKeysUpdatePage.setLongitudeInput('5'),
      heroKeysUpdatePage.setAgeInput('5'),
      heroKeysUpdatePage.setMyPositionInput('5'),
      heroKeysUpdatePage.setSkillInput('5'),
      heroKeysUpdatePage.heroDetailsSelectLastOption(),
    ]);

    await heroKeysUpdatePage.save();
    expect(await heroKeysUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await heroKeysComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last HeroKeys', async () => {
    const nbButtonsBeforeDelete = await heroKeysComponentsPage.countDeleteButtons();
    await heroKeysComponentsPage.clickOnLastDeleteButton();

    heroKeysDeleteDialog = new HeroKeysDeleteDialog();
    expect(await heroKeysDeleteDialog.getDialogTitle()).to.eq('hockeyheroApp.heroKeys.delete.question');
    await heroKeysDeleteDialog.clickOnConfirmButton();
    await browser.wait(ec.visibilityOf(heroKeysComponentsPage.title), 5000);

    expect(await heroKeysComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
