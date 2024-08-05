import { Helmet } from "react-helmet-async"; // Importing Helmet for managing document head asynchronously

export default function NotFound() {
    const handleGoBack = () => {
       history.back(); // Function to navigate back in history
    };

    return (
        <>
        <Helmet>
            <title>404 - Page Not Found</title> {/* Setting page title */}
            <meta name="description" content="404 - Page Not Found" /> {/* Setting meta description */}
        </Helmet>
       <main className="grid min-h-screen place-items-center bg-white px-6 py-24 sm:py-32 lg:px-8">
          <div className="text-center">
             <p className="text-5xl font-semibold text-primary">404!</p> {/* Heading for 404 error */}
             <h1 className="mt-4 text-3xl font-bold tracking-tight text-gray-900 sm:text-5xl">Page not found</h1> {/* Main heading for page not found */}
             <p className="text-base leading-5 text-gray-500 max-w-sm pt-4">
                The page you are looking for was not found. If you think this is an issue, please contact support for help {/* Description for page not found */}
             </p>
             <div className="mt-10 flex items-center justify-center gap-x-6">
                <button
                   onClick={handleGoBack} // Button to go back using history
                   className="rounded-md bg-primary px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-light-blue focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-dark-blue"
                >
                   Go back
                </button>
                <div role="button" className="text-sm font-semibold text-gray-900">
                   Contact support <span aria-hidden="true">&rarr;</span> {/* Link to contact support */}
                </div>
             </div>
          </div>
       </main>
        </>
    );
}