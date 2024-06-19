export class User {
  id: number = 0;
  email: string = '';
  username: string = '';
  localisation: string = '';
  num_tel: number = 0;
  password: string = '';
  confirmPassword: string = '';
  role: string = '';
  token: string = '';
  active!: boolean;
  name: any;
  description : string = '';
}
