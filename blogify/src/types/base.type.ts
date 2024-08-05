/* eslint-disable @typescript-eslint/no-explicit-any */
export interface IEntity {
    id: string;
    createdAt: Date | string;
    updatedAt: Date | string;

}export interface ApiResponse<T = any> {
    data: T;
    success: boolean;
    message: string;
}

export interface IPagination<T = any[]> {
    content: T;
    empty: boolean;
    first: boolean;
    last: boolean;
    number: number;
    numberOfElements: number;
    pageable: Pageable;
    size: number;
    sort: Sort;
    totalElements: number;
    totalPages: number;
}

export interface Pageable {
    offset: number;
    pageNumber: number;
    pageSize: number;
    paged: boolean;
    sort: Sort;
    unpaged: boolean;
}

export interface Sort {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
}

export interface IPaginatedQuery {
    limit?: number;
    page?: number;
    sort?: string;
    filter?: string;
  }
export enum ERole{
    PUBLISHER = 'PUBLISHER',
    READER = 'COMMENTER'
}