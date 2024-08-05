export interface IBlog {
    id:string;
    title: string;
    authorFirstName:string;
    authorLastName: string;
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    content: any;
    coverImage?: string;
    createdAt: string;

    


}