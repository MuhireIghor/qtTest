
import { BrowserRouter as Router, Routes, Route, Navigate, Outlet } from 'react-router-dom'
import LoginPage from './pages/auth/login'
import SignupPage from './pages/auth/signup'
import MainLayout from './layouts/MainLayout';
import BlogsPage from './pages/blogs';
import ViewSingleBlogComponent from './pages/blogs/ViewBlog';
import AccountPage from './pages/account';
import CreateOrUpdateBlog from './pages/blogs/create-blog';
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
        <Route path='/' index element={<LoginPage />} />
        <Route path='/auth/login' element={<LoginPage />} />
        <Route path="/blogs" element={<BlogsPage />} />
        <Route path="/blogs/:blogId" element={<ViewSingleBlogComponent />} />
        <Route path='/auth/signup' element={<SignupPage />} />
        <Route element={<AuthRoute />}>
          <Route path="/blogs/create" element={<CreateOrUpdateBlog />} />
          <Route path="/account" element={<AccountPage />} />
        </Route>
      </Routes>
    </Router>

  )
}

export default App
