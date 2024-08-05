/* eslint-disable @typescript-eslint/no-explicit-any */
import MainLayout from "@/layouts/MainLayout";
import BlogCardComponent from "../../components/blogs";
import useGet from "@/hooks/useGet";
import EmptyView from "@/views/EmptyView";
import { Loader } from "@mantine/core";

const BlogsPage = () => {
    const {
        data: blogs,
        loading,
        error

    } = useGet<any[]>("/blogs/all-blogs", {
        defaultData: [],
    });
    return (
        <MainLayout>

            <section className=" grid grid-cols-1 md:grid-cols-2  lg:grid-cols-3 gap-x-4 gap-y-2 md:gap-y-3 lg:gap-y-6">
                {
                   !loading && !error && blogs && blogs.length === 0 &&
                    (
                        <EmptyView message="No blogs Posted Yet" />
                    )
                }
                {
                    !loading && error && (
                        <EmptyView message="Failed to fetch blogs" />
                    )
                }
                {
                    loading && (
                        <Loader />
                    )
                }

                {blogs && blogs.map((blog, index) => {
                    return (
                        <BlogCardComponent key={index} {...blog} />
                    )
                })}
            </section>
        </MainLayout>
    )
}
export default BlogsPage;