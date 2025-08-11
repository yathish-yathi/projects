import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestautantListing } from './restautant-listing';

describe('RestautantListing', () => {
  let component: RestautantListing;
  let fixture: ComponentFixture<RestautantListing>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestautantListing]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RestautantListing);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
