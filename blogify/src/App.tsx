
import { BrowserRouter as Router, Routes, Route, Navigate, Outlet } from 'react-router-dom'
import SuspenseWithLoader from './components/common/suspense';
import { lazy } from 'react';
import MainLayout from './layouts/MainLayout';
const CreateOrUpdateBlog = lazy(() => import('./pages/blogs/create-blog'));
const AccountPage = lazy(() => import('./pages/account'))
const ViewSingleBlogComponent = lazy(() => import('./pages/blogs/ViewBlog'));
const BlogsPage = lazy(() => import('./pages/blogs'))
const NotFound = lazy(() => import('./pages/404'));
const SignupPage = lazy(() => import('./pages/auth/signup'));
const LoginPage = lazy(() => import('./pages/auth/login'));
function App() {
  const AuthRoute = () => {
    const token = sessionStorage.getItem("token");
    if (token) {
      return (
        <div className='h-screen'>
          <MainLayout>
            <Outlet />

          </MainLayout>

        </div>

      )

    }
    return <Navigate to="/" />;
  };
  return (
    <Router>
      <Routes>
        <Route path='/' index element={<SuspenseWithLoader><LoginPage /></SuspenseWithLoader>} />
        <Route path='/auth/login' element={<SuspenseWithLoader><LoginPage /></SuspenseWithLoader>} />
        <Route path="/blogs" element={<SuspenseWithLoader><BlogsPage /></SuspenseWithLoader>} />
        <Route path="/blogs/:blogId" element={<SuspenseWithLoader><ViewSingleBlogComponent /></SuspenseWithLoader>} />
        <Route path='/auth/signup' element={<SuspenseWithLoader><SignupPage /></SuspenseWithLoader>} />
        <Route element={<AuthRoute />}>
          <Route path="/blogs/create" element={<SuspenseWithLoader><CreateOrUpdateBlog /></SuspenseWithLoader>} />
          <Route path="/account" element={<SuspenseWithLoader><AccountPage /></SuspenseWithLoader>} />
        </Route>
        <Route
          path="*"
          element={
            <SuspenseWithLoader>
              <NotFound />
            </SuspenseWithLoader>
          }
        />
      </Routes>
    </Router>

  )
}

export default App
