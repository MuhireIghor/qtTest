import { ERole, IEntity } from "./base.type";

export interface IUser extends IEntity {
    firstName: string;
    lastName: string;
    email: string;
    role: "PUBLISHER" | "COMMENTER",

}
export interface IUserDto {
    firstName: string;    lastName: string;
    email: string;
    password: string;

}