export interface IBlog {
    title: string;
    author: string;
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    content: any;
    coverImage?: string;
    createdAt: string;


}