import { IEntity } from "./base.type";

export interface IBlogDto extends IEntity {
    title: string;
    content: string;
}
export interface IBlog extends IEntity {
    title:string;
    content:string;
    
}