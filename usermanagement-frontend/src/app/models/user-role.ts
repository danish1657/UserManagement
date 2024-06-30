export interface UserRole {
  id: number;
  user: any;  // You might want to create a User model as well
  unit: any;  // You might want to create a Unit model as well
  role: any;  // You might want to create a Role model as well
  validFrom: string;
  validTo: string;
  version: number;
}
