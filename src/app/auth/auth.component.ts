import { Component } from '@angular/core';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
})
export class AuthComponent {
  showSignin = true; 

  toggleView() {
    this.showSignin = !this.showSignin;
  }
}
