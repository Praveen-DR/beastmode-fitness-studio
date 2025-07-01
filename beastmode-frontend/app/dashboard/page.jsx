// app/dashboard/page.jsx
'use client';
import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import { api } from '../../lib/api';
import { auth } from '../../utils/auth';
import Navbar from '../../components/Navbar';
import Button from '../../components/Button';

export default function DashboardPage() {
  const router = useRouter();
  const [userData, setUserData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  // Fetch user data on page load
  useEffect(() => {
    if (!auth.isAuthenticated()) {
      router.push('/login');
      return;
    }

    const fetchUserData = async () => {
      try {
        const userId = auth.getPayload(); // Get user ID from JWT payload
        const data = await api.fetch(`/GetUserById?userId=${userId}`);
        setUserData(data);
      } catch (err) {
        setError(err.message || 'Failed to fetch user data');
      } finally {
        setLoading(false);
      }
    };

    fetchUserData();
  }, [router]);

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <p className="text-gray-600">Loading dashboard...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <p className="text-red-500">{error}</p>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50">
      {/* <Navbar /> */}

      <div className="max-w-4xl mx-auto p-6">
        <div className="bg-white rounded-lg shadow p-6">
          <h1 className="text-2xl font-bold mb-4">Welcome, {userData.name}</h1>

          <div className="space-y-4">
            <div>
              <h2 className="text-lg font-semibold">Your Information</h2>
              <div className="mt-2 space-y-2">
                <p><strong>Email:</strong> {userData.email}</p>
                <p><strong>Role:</strong> {userData.role}</p>
                {userData.trainer && (
                  <p><strong>Trainer:</strong> {userData.trainer.name}</p>
                )}
              </div>
            </div>

            {userData.role === 'MEMBER' && (
              <div>
                <h2 className="text-lg font-semibold">Member Actions</h2>
                <div className="mt-2 space-x-4">
                  <Button onClick={() => router.push('/workouts')}>
                    View Workouts
                  </Button>
                  <Button onClick={() => router.push('/payments')}>
                    Payment History
                  </Button>
                </div>
              </div>
            )}

            {userData.role === 'TRAINER' && (
              <div>
                <h2 className="text-lg font-semibold">Trainer Actions</h2>
                <div className="mt-2 space-x-4">
                  <Button onClick={() => router.push('/manage-members')}>
                    Manage Members
                  </Button>
                  <Button onClick={() => router.push('/create-workout')}>
                    Create Workout Plan
                  </Button>
                </div>
              </div>
            )}

            {userData.role === 'ADMIN' && (
              <div>
                <h2 className="text-lg font-semibold">Admin Actions</h2>
                <div className="mt-2 space-x-4">
                  <Button onClick={() => router.push('/manage-users')}>
                    Manage Users
                  </Button>
                  <Button onClick={() => router.push('/reports')}>
                    View Reports
                  </Button>
                </div>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}