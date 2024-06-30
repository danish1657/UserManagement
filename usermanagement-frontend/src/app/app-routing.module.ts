// src/app/app-routing.module.ts

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component'; // Adjust path as per your structure
import { UnitListComponent } from './unit-list/unit-list.component';
import { RolesComponent } from './roles/roles.component';
import { UserRolesComponent } from './user-roles/user-roles.component';

const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: '', redirectTo: '/users', pathMatch: 'full' },  
  { path: 'units', component: UnitListComponent },
  { path: 'roles', component: RolesComponent },
  { path: 'userroles/:userId/:unitId/:timestamp', component: UserRolesComponent },
  { path: '**', redirectTo: '/users' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
