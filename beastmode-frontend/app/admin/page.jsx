'use client';
import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { auth } from '../../utils/auth';
import UsersTab from './components/UsersTab';
import MembershipsTab from './components/MembershipsTab';
import PaymentsTab from './components/PaymentsTab';
import AttendanceTab from './components/AttendanceTab';
import FeedbackTab from './components/FeedbackTab';

export default function AdminPage() {
  const router = useRouter();
  const [activeTab, setActiveTab] = useState('users');

  // Redirect if not an admin
  if (!auth.isAuthenticated() || auth.getUserRole() !== 'ADMIN') {
    router.push('/login');
    return null;
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-7xl mx-auto p-6">
        <h1 className="text-2xl font-bold mb-6">Admin Dashboard</h1>

        {/* Navigation Tabs */}
        <div className="flex space-x-4 mb-6 border-b border-gray-200">
          <button
            onClick={() => setActiveTab('users')}
            className={`px-4 py-2 ${activeTab === 'users' ? 'border-b-2 border-indigo-600 text-indigo-600' : 'text-gray-500'}`}
          >
            Users
          </button>
          <button
            onClick={() => setActiveTab('memberships')}
            className={`px-4 py-2 ${activeTab === 'memberships' ? 'border-b-2 border-indigo-600 text-indigo-600' : 'text-gray-500'}`}
          >
            Memberships
          </button>
          <button
            onClick={() => setActiveTab('payments')}
            className={`px-4 py-2 ${activeTab === 'payments' ? 'border-b-2 border-indigo-600 text-indigo-600' : 'text-gray-500'}`}
          >
            Payments
          </button>
          <button
            onClick={() => setActiveTab('attendance')}
            className={`px-4 py-2 ${activeTab === 'attendance' ? 'border-b-2 border-indigo-600 text-indigo-600' : 'text-gray-500'}`}
          >
            Attendance
          </button>
          <button
            onClick={() => setActiveTab('feedback')}
            className={`px-4 py-2 ${activeTab === 'feedback' ? 'border-b-2 border-indigo-600 text-indigo-600' : 'text-gray-500'}`}
          >
            Feedback
          </button>
        </div>

        {/* Tab Content */}
        <div>
          {activeTab === 'users' && <UsersTab />}
          {activeTab === 'memberships' && <MembershipsTab />}
          {activeTab === 'payments' && <PaymentsTab />}
          {activeTab === 'attendance' && <AttendanceTab />}
          {activeTab === 'feedback' && <FeedbackTab />}
        </div>
      </div>
    </div>
  );
}