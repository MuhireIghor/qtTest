import { Suspense } from "react";
import LoaderComponent from "@/components/common/ui/Loader"

const SuspenseLoader = () => {
    return (
        <div className="flex items-center justify-center h-screen ">
            <div className="animate-pulse flex flex-col  items-center">
                {/* <Logo /> */}
                <div className="flex items-center mt-4 gap-4">
                    <LoaderComponent />
                    <h1 className=""> Loading...</h1>
                </div>
            </div>
        </div>
    )

}
const SuspenseWithLoader = ({ children }: { children: React.ReactNode }) => {
    return (
        <Suspense fallback={<SuspenseLoader />}>
            {children}
        </Suspense>
    )
}
export default SuspenseWithLoader;