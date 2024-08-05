/* eslint-disable @typescript-eslint/no-explicit-any */
// import { IBlog } from "@/types";
import MainLayout from "@/layouts/MainLayout";
import BlogCardComponent from "../../components/blogs";
import { data } from "../../data";
import useGet from "@/hooks/useGet";

const BlogsPage = () => {
    const {
        data: blogs,
        get,
        loading,
        error,
    } = useGet<any[]>("/blogs/all-blogs", {
        defaultData: [],
    });
    console.log("here are the blogss", blogs)
    return (
        <MainLayout>

            <section className=" grid grid-cols-3 gap-x-4 gap-y-2">
                {data && data.map((blog, index) => {
                    return (
                        <BlogCardComponent key={index} {...blog} />
                    )
                })}
            </section>
        </MainLayout>
    )
}
export default BlogsPage;