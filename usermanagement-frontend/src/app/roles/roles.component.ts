import { Component, OnInit } from '@angular/core';
import { RoleService } from '../services/role.service';
import { Role } from '../models/role';

@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.css']
})
export class RolesComponent implements OnInit {
  roles: Role[] = [];
  newRole: Role = { id: 0, name: '', version: 0 };

  constructor(private roleService: RoleService) { }

  ngOnInit(): void {
    this.getAllRoles();
  }

  getAllRoles(): void {
    this.roleService.getAllRoles().subscribe((roles: Role[]) => {
      this.roles = roles;
    });
  }

  createRole(): void {
    this.roleService.createRole(this.newRole).subscribe((role: Role) => {
      this.roles.push(role);
      this.newRole = { id: 0, name: '', version: 0 }; // Reset form
    });
  }

  updateRole(role: Role): void {
    this.roleService.updateRole(role).subscribe((updatedRole: Role) => {
      const index = this.roles.findIndex(r => r.id === updatedRole.id);
      if (index !== -1) {
        this.roles[index] = updatedRole;
      }
    });
  }

  deleteRole(roleId: number): void {
    this.roleService.deleteRole(roleId).subscribe(() => {
      this.roles = this.roles.filter(role => role.id !== roleId);
    });
  }
}
