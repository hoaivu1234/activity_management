import { cloneDeep } from 'lodash';
import { Component, OnInit } from '@angular/core';
import { LecturerManagementComponent } from '../lecturer-management/lecturer-management.component';
import { LecturerServiceService } from 'src/app/service/lecturer-service.service';
import { ProfessionalGroupService } from 'src/app/service/professional-group.service';
import { ProjectService } from 'src/app/service/project.service';

@Component({
  selector: 'app-project-management',
  templateUrl: './project-management.component.html',
  styleUrls: ['./project-management.component.scss']
})
export class ProjectManagementComponent extends LecturerManagementComponent {
  constructor(
    public lecturerServiceService: LecturerServiceService,
    public projectService: ProjectService
  ) {
    super(lecturerServiceService);
  }

  async fetchData() {
    this.isLoadingTable = true;
    const queryString = this.buildQueryString();
    await this.projectService.getProjectFilter(queryString)
      .toPromise()
      .then((res: any) => {
        if (res) {
          this.listOfData = cloneDeep(res.content); 
          this.total = res.totalRecords;
        }
      })
      .finally(() => {
        this.isLoadingTable = false;
      });
  }
}
