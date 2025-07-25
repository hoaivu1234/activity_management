import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent {
  queryModel: any;

  credentials = {
    username: '',
    password: ''
  };

  constructor(public userService: UserService, private message: NzMessageService, private router: Router) { }

  buildQueryString() {
    this.queryModel = {
      username: this.credentials.username,
      password: this.credentials.password,
    };
    return this.queryModel;
  }

  async login() {
    const queryString = this.buildQueryString();
    await this.userService.signIn(queryString)
    .toPromise()
    .then((res: any) => {
      if (res) {
        localStorage.setItem('access_token', res.accessToken);
        localStorage.setItem('userName', this.queryModel.username);
        localStorage.setItem('listRoles', res.role);
        this.message.success("Đăng nhập thành công");
        this.router.navigate(['/dashboard']);
      }
    })
    .catch((error) => {
      this.message.error("Đăng nhập thất bại!!!");
    })
  }

}
