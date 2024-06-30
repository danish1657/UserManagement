//import { Component } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { UnitService } from '../services/unit.service';
import { Unit } from '../models/unit';

@Component({
  selector: 'app-unit-list',
  templateUrl: './unit-list.component.html',
  styleUrls: ['./unit-list.component.css']
})
export class UnitListComponent implements OnInit {
  unit: Unit[] = [];
  newUnit: Unit = { id: 0, name: '', version: 1 };
  isEdit: boolean = false;						  

  constructor(private unitService: UnitService) { }

  ngOnInit(): void {
	this.loadUnits();
  }

  loadUnits(): void {
    this.unitService.getUnits().subscribe(unit => {
      this.unit = unit;
    });
  }

  createOrUpdateUnit(): void {
    if (this.isEdit) {
      this.updateUnit(this.newUnit);
    } else {
      this.unitService.createUnit(this.newUnit).subscribe(unit => {
					   
        this.unit.push(unit);
        this.newUnit = { id: 0, name: '', version: 0 }; // Reset form
      });
    }
    this.isEdit = false;
  }

  editUnit(unit: Unit): void {
    this.newUnit = { ...unit };
    this.isEdit = true;
  }

  updateUnit(unit: Unit): void {
    this.unitService.updateUnit(unit.id, unit).subscribe(updatedUnit => {
							  
      const index = this.unit.findIndex(u => u.id === updatedUnit.id);
      if (index !== -1) {
        this.unit[index] = updatedUnit;
      }
      this.newUnit = { id: 0, name: '', version: 0 }; // Reset form
    });
	  
  }

  deleteUnit(unitId: number): void {
    this.unitService.deleteUnit(unitId).subscribe(() => {
      this.unit = this.unit.filter(unit => unit.id !== unitId);
    });
	  
  }
}
