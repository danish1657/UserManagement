import { Routes } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { UnitListComponent } from './unit-list/unit-list.component';

export const routes: Routes = [
  { path: '', redirectTo: '/users', pathMatch: 'full' },
  { path: 'users', component: UserListComponent },
  { path: 'units', component: UnitListComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }