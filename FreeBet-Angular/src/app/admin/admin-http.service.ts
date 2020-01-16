import {Injectable} from '@angular/core';



import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AppConfigService} from '../app-config.service';
import {Admin} from './admin';

@Injectable({
  providedIn: 'root'
})
export class AdminHttpService {

  admins: Array<Admin>;

  constructor(private appConfig: AppConfigService, private adminService: AdminHttpService, private loginService: LoginHttpService, private http: HttpClient) {
    this.load();
  }

  load() {
    this.http.get<Array<Admin>>(this.appConfig.backEnd + 'admin').subscribe(resp => {
        this.admins = resp;
      },
      err => console.log(err));
  }

  findAll(): Array<Admin> {
    return this.admins;
  }

  findById(id: number): Observable<Admin> {
    return this.http.get<Admin>(this.appConfig.backEnd + 'admin/' + id);
  }

  save(admin: Admin) {
    if (admin) {
      if (admin.login && !admin.login.id) {
        admin.login = null;
      }
      if (!admin.id) {
        this.http.post<Admin>(this.appConfig.backEnd + 'admin', admin).subscribe(resp => {
          this.load();
        }, err => console.log(err));
      } else {
        this.http.put<Admin>(this.appConfig.backEnd + 'admin/' + admin.id, admin).subscribe(resp => {
          this.load();
        }, err => console.log(err));
      }
    }
  }

  delete(id: number) {
    this.http.delete<Admin>(this.appConfig.backEnd + 'admin/' + id).subscribe(resp => {
      this.load();
    }, err => console.log(err));
  }
}
