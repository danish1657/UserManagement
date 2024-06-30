// src/app/user.ts

export interface User {
  id: number;
  version: number;
  name: string;
  unitName: string; // Define unitName property
  roles: string[]; // Define roles property as array of strings or as needed
}
