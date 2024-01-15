import {Component, Input} from '@angular/core';
import { SvgIcon } from 'src/app/model/enum/svg-icon.enum';

@Component({
    selector: 'todo-svg-icon',
    templateUrl: './svg-icon.component.html',
    styleUrls: ['./svg-icon.component.scss'],
})
export class SvgIconComponent {
    @Input() public icon: SvgIcon;

    public SVG_ICON = SvgIcon;
}

