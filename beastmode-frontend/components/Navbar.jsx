
'use client';
import Link from 'next/link';
import { auth } from '../utils/auth';
import Button from './Button';

export default function Navbar() {
  const isLoggedIn = auth.isAuthenticated();
  const userRole = auth.getUserRole();

  return (
    <nav className="bg-white shadow-lg">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16 items-center">
          <div className="flex items-center space-x-4">
            <Link href="/" className="text-xl font-bold text-gray-800">
              BeastMode Gym
            </Link>
            {isLoggedIn && (
              <Link 
                href="/dashboard" 
                className="text-gray-600 hover:text-indigo-600"
              >
                Dashboard
              </Link>
            )}
          </div>
          
          <div className="flex items-center space-x-4">
            {isLoggedIn ? (
              <>
                {userRole === 'ADMIN' && (
                  <Link
                    href="/admin"
                    className="text-gray-600 hover:text-indigo-600"
                  >
                    Admin
                  </Link>
                )}
                <Button onClick={() => {
                  auth.clearToken();
                  window.location.href = '/login';
                }}>
                  Logout
                </Button>
              </>
            ) : (
              <>
                <Link href="/login" className="text-gray-600 hover:text-indigo-600">
                  Login
                </Link>
                <Link href="/register" className="text-gray-600 hover:text-indigo-600">
                  Register
                </Link>
              </>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
}

