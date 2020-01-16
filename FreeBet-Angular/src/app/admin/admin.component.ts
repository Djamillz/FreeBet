import { Component, OnInit } from '@angular/core';
import {Admin} from './admin';
import {AdminHttpService} from './admin-http.service';
import {Login} from '../login/Login';

@Component({
  selector: 'admin, [admin]',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  currentAdmin: Admin = null;

  modalAdmin: Admin = null;

  constructor(private modalService: NgbModal, private adminService: AdminHttpService, private loginService: LoginHttpService) { }

  ngOnInit() {
  }

  list() {
    return this.adminService.findAll();
  }

  formateurs() {
    return this.loginService.findAll();
  }

  add() {
    this.currentAdmin = new Admin();
    this.currentAdmin.adresse = new Adresse();
    this.currentAdmin.login = new Login();
  }

  detail(content, id: number) {
    this.adminService.findById(id).subscribe(resp => {
      this.modalAdmin = resp;
    }, error => {
      console.log(error);
    });
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', scrollable: true});
  }

  edit(id: number) {
    this.adminService.findById(id).subscribe(resp => {
      this.currentAdmin = resp;

      if (!this.currentAdmin.login) {
        this.currentAdmin.login = new Login();
      }
      if (!this.currentAdmin.adresse) {
        this.currentAdmin.adresse = new Adresse();
      }


    }, error => {
      console.log(error);
    });

  }

  save() {
    this.adminService.save(this.currentAdmin);

    this.cancel();
  }

  delete(id: number) {
    this.adminService.delete(id);
  }

  cancel() {
    this.currentAdmin = null;
  }



}
