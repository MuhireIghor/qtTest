import { AuthAPi, getResError } from "@/config/axios.config";
import { Button, Input, Textarea } from "@mantine/core";
import { notifications } from "@mantine/notifications";
import React from "react";


const CreateOrUpdateBlog = () => {
    const [data, setData] = React.useState({
        title: "",
        content: "",
    });
    const [loading, setLoading] = React.useState(false);
    const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!data.title.trim() || !data.content.trim()) {
            notifications.show({
                title: "Blog creation failed",
                message: "Please fill all required fields",
                color: "red",
            });
            return;
        }
        setLoading(true);
        console.log("data", data);
        try {
            const res = await AuthAPi.post("/blogs/create", data);
            console.log(res);
            if (res.data) {
                notifications.show({
                    title: "Add Blog Success",
                    message: "Blog created successfully",
                    color: "green",
                });
            }

        } catch (error) {
            console.log(error);
            notifications.show({
                title: "Blog creation Failed",
                message: getResError(error),
                color: "red",
            });
        }
        setLoading(false);
    };
    return (
        <section>
            <form
                onSubmit={onSubmit}
                className=" w-full flex-col flex gap-y-4 py-4 items-center"
            >
                <div className="flex mt-5 w-full flex-col gap-y-4">
                 <label className="font-semibold">Title</label>
                        <Input className="border"
                            onChange={(e) =>
                                setData((prev) => ({ ...prev, title: e.target.value }))
                            }
                            required
                            placeholder="Name"
                            
                            variant="filled"
                            size="md"
                        />
                    <label className="font-semibold">Content</label>
                    <Textarea
                    className="border"
                        onChange={(e) =>
                            setData((prev) => ({ ...prev, content: e.target.value }))
                        }

                        required
                        placeholder="Blog content"
                        p={2}
                        variant="filled"
                        size="md"
                    />
                </div>
                <Button
                    type="submit"
                    radius="md"
                    loading={loading}
                    disabled={loading}
                    w={"30%"}
                    size="md"
                    mt={8}
                    className=" w-full bg-primary text-white "
                >
                    Post Here
                </Button>
            </form>
        </section>
    )

}
export default CreateOrUpdateBlog;