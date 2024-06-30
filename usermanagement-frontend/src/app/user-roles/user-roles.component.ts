import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserRoleService } from '../services/user-role.service';
import { UserRole } from '../models/user-role';

@Component({
  selector: 'app-user-roles',
  templateUrl: './user-roles.component.html',
  styleUrls: ['./user-roles.component.css']
})
export class UserRolesComponent implements OnInit {
  userRoles: UserRole[] = [];
  newUserRole: UserRole = { id: 0, user: {}, unit: {}, role: {}, validFrom: '', validTo: '', version: 0 };

  constructor(private route: ActivatedRoute,
  private userRoleService: UserRoleService) { }

  ngOnInit(): void {
    // Initial data load if necessary
	const userId = this.route.snapshot.paramMap.get('userId');
    const unitId = this.route.snapshot.paramMap.get('unitId');
    const timestamp = this.route.snapshot.paramMap.get('timestamp');

    this.userRoleService.getUserRoles(Number(userId), Number(unitId), String(timestamp))
      .subscribe(
        data => {
          this.userRoles = data;
          console.log('User Roles:', this.userRoles);
        },
        error => {
          console.error('There was an error!', error);
        })
  }

  getUserRoles(userId: number, unitId: number, timestamp: string): void {
    this.userRoleService.getUserRoles(userId, unitId, timestamp).subscribe((userRoles: UserRole[]) => {
      this.userRoles = userRoles;
    });
  }

  createUserRole(): void {
    this.userRoleService.createUserRole(this.newUserRole).subscribe((userRole: UserRole) => {
      this.userRoles.push(userRole);
      this.newUserRole = { id: 0, user: {}, unit: {}, role: {}, validFrom: '', validTo: '', version: 0 }; // Reset form
    });
  }

  updateUserRole(userRole: UserRole): void {
    this.userRoleService.updateUserRole(userRole.id, userRole).subscribe((updatedUserRole: UserRole) => {
      const index = this.userRoles.findIndex(ur => ur.id === updatedUserRole.id);
      if (index !== -1) {
        this.userRoles[index] = updatedUserRole;
      }
    });
  }

  deleteUserRole(userRoleId: number): void {
    this.userRoleService.deleteUserRole(userRoleId).subscribe(() => {
      this.userRoles = this.userRoles.filter(ur => ur.id !== userRoleId);
    });
  }

  getValidUserRoles(userId: number, unitId: number, timestamp: string): void {
    this.userRoleService.getValidUserRoles(userId, unitId, timestamp).subscribe((validUserRoles: UserRole[]) => {
      this.userRoles = validUserRoles;
    });
  }
}
