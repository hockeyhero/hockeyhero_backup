import { element, by, ElementFinder } from 'protractor';

export class HeroKeysComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-hero-keys div table .btn-danger'));
  title = element.all(by.css('jhi-hero-keys div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class HeroKeysUpdatePage {
  pageTitle = element(by.id('jhi-hero-keys-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idInput = element(by.id('field_id'));
  hideMeInput = element(by.id('field_hideMe'));
  latitudeInput = element(by.id('field_latitude'));
  longitudeInput = element(by.id('field_longitude'));
  ageInput = element(by.id('field_age'));
  myPositionInput = element(by.id('field_myPosition'));
  skillInput = element(by.id('field_skill'));

  heroDetailsSelect = element(by.id('field_heroDetails'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdInput(id: string): Promise<void> {
    await this.idInput.sendKeys(id);
  }

  async getIdInput(): Promise<string> {
    return await this.idInput.getAttribute('value');
  }

  getHideMeInput(): ElementFinder {
    return this.hideMeInput;
  }

  async setLatitudeInput(latitude: string): Promise<void> {
    await this.latitudeInput.sendKeys(latitude);
  }

  async getLatitudeInput(): Promise<string> {
    return await this.latitudeInput.getAttribute('value');
  }

  async setLongitudeInput(longitude: string): Promise<void> {
    await this.longitudeInput.sendKeys(longitude);
  }

  async getLongitudeInput(): Promise<string> {
    return await this.longitudeInput.getAttribute('value');
  }

  async setAgeInput(age: string): Promise<void> {
    await this.ageInput.sendKeys(age);
  }

  async getAgeInput(): Promise<string> {
    return await this.ageInput.getAttribute('value');
  }

  async setMyPositionInput(myPosition: string): Promise<void> {
    await this.myPositionInput.sendKeys(myPosition);
  }

  async getMyPositionInput(): Promise<string> {
    return await this.myPositionInput.getAttribute('value');
  }

  async setSkillInput(skill: string): Promise<void> {
    await this.skillInput.sendKeys(skill);
  }

  async getSkillInput(): Promise<string> {
    return await this.skillInput.getAttribute('value');
  }

  async heroDetailsSelectLastOption(): Promise<void> {
    await this.heroDetailsSelect.all(by.tagName('option')).last().click();
  }

  async heroDetailsSelectOption(option: string): Promise<void> {
    await this.heroDetailsSelect.sendKeys(option);
  }

  getHeroDetailsSelect(): ElementFinder {
    return this.heroDetailsSelect;
  }

  async getHeroDetailsSelectedOption(): Promise<string> {
    return await this.heroDetailsSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class HeroKeysDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-heroKeys-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-heroKeys'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
