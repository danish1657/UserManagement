// user-list.component.ts

import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service'; // Adjust path as per your structure
import { User } from '../models/user'; // Adjust path as per your structure

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  newUser: User = { id: 0, version: 0, name: '', unitName: '', roles: [] }; // Adjust default values

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers(): void {
    this.userService.getUsers()
      .subscribe((users: User[]) => this.users = users); // Specify User[] type
  }

  createUser(): void {
    this.userService.createUser(this.newUser)
      .subscribe((user: User) => {
        this.users.push(user);
        this.newUser = { id: 0, version: 0, name: '', unitName: '', roles: [] }; // Reset form fields after creation
      });
  }

  updateUser(user: User): void {
    this.userService.updateUser(user)
      .subscribe((updatedUser: User) => {
        const index = this.users.findIndex(u => u.id === updatedUser.id);
        if (index !== -1) {
          this.users[index] = updatedUser;
        }
      });
  }

  deleteUser(userId: number): void {
    this.userService.deleteUser(userId)
      .subscribe(() => {
        this.users = this.users.filter(u => u.id !== userId);
      });
  }
}
