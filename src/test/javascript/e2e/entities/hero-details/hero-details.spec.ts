import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { HeroDetailsComponentsPage, HeroDetailsDeleteDialog, HeroDetailsUpdatePage } from './hero-details.page-object';

const expect = chai.expect;

describe('HeroDetails e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let heroDetailsComponentsPage: HeroDetailsComponentsPage;
  let heroDetailsUpdatePage: HeroDetailsUpdatePage;
  let heroDetailsDeleteDialog: HeroDetailsDeleteDialog;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing(username, password);
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load HeroDetails', async () => {
    await navBarPage.goToEntity('hero-details');
    heroDetailsComponentsPage = new HeroDetailsComponentsPage();
    await browser.wait(ec.visibilityOf(heroDetailsComponentsPage.title), 5000);
    expect(await heroDetailsComponentsPage.getTitle()).to.eq('hockeyheroApp.heroDetails.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(heroDetailsComponentsPage.entities), ec.visibilityOf(heroDetailsComponentsPage.noResult)),
      1000
    );
  });

  it('should load create HeroDetails page', async () => {
    await heroDetailsComponentsPage.clickOnCreateButton();
    heroDetailsUpdatePage = new HeroDetailsUpdatePage();
    expect(await heroDetailsUpdatePage.getPageTitle()).to.eq('hockeyheroApp.heroDetails.home.createOrEditLabel');
    await heroDetailsUpdatePage.cancel();
  });

  it('should create and save HeroDetails', async () => {
    const nbButtonsBeforeCreate = await heroDetailsComponentsPage.countDeleteButtons();

    await heroDetailsComponentsPage.clickOnCreateButton();

    await promise.all([
      heroDetailsUpdatePage.setPhoneInput('phone'),
      heroDetailsUpdatePage.setDateOfBirthInput('2000-12-31'),
      heroDetailsUpdatePage.setStreetAddressInput('streetAddress'),
      heroDetailsUpdatePage.setCityInput('city'),
      heroDetailsUpdatePage.setStateProvinceInput('stateProvince'),
      heroDetailsUpdatePage.setPostalCodeInput('postalCode'),
      heroDetailsUpdatePage.userSelectLastOption(),
    ]);

    await heroDetailsUpdatePage.save();
    expect(await heroDetailsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await heroDetailsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last HeroDetails', async () => {
    const nbButtonsBeforeDelete = await heroDetailsComponentsPage.countDeleteButtons();
    await heroDetailsComponentsPage.clickOnLastDeleteButton();

    heroDetailsDeleteDialog = new HeroDetailsDeleteDialog();
    expect(await heroDetailsDeleteDialog.getDialogTitle()).to.eq('hockeyheroApp.heroDetails.delete.question');
    await heroDetailsDeleteDialog.clickOnConfirmButton();
    await browser.wait(ec.visibilityOf(heroDetailsComponentsPage.title), 5000);

    expect(await heroDetailsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
