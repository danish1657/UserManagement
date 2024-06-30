// src/app/app.module.ts

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module'; // Ensure correct path
import { UserListComponent } from './user-list/user-list.component';
import { UnitListComponent } from './unit-list/unit-list.component';
import { RolesComponent } from './roles/roles.component';
import { UserRolesComponent } from './user-roles/user-roles.component';
import { UserRoleService } from './services/user-role.service';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
	UnitListComponent,
	RolesComponent,
	UserRolesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule // Ensure AppRoutingModule is correctly imported here
  ],
  providers: [UserRoleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
