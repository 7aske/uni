import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'toDate'
})
export class ToDatePipe implements PipeTransform {

  transform(value: string|undefined, ...args: unknown[]): Date {
    if (!value) return new Date();
    return new Date(value);
  }

}
