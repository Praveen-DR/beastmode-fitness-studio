'use client';
import { useEffect, useState } from 'react';
import { api } from '../../../lib/api';
import Button from '../../../components/Button';

export default function UsersTab() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const data = await api.fetch('/GetAllUser', { method: 'GET' });
        setUsers(data);
      } catch (err) {
        setError(err.message || 'Failed to fetch users');
      } finally {
        setLoading(false);
      }
    };

    fetchUsers();
  }, []);

  const handleDelete = async (userId) => {
    try {
      await api.fetch(`/DeleteUser?userId=${userId}`, { method: 'GET' });
      setUsers(users.filter((user) => user.userId !== userId));
    } catch (err) {
      setError(err.message || 'Failed to delete user');
    }
  };

  if (loading) {
    return <div className="text-center p-8">Loading users...</div>;
  }

  if (error) {
    return <div className="text-center p-8 text-red-500">{error}</div>;
  }

  return (
    <div className="overflow-x-auto">
      <table className="min-w-full bg-white rounded-lg shadow">
        <thead>
          <tr>
            <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
              Name
            </th>
            <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
              Email
            </th>
            <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
              Role
            </th>
            <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
              Actions
            </th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.userId} className="hover:bg-gray-50">
              <td className="px-6 py-4 border-b border-gray-200">{user.name}</td>
              <td className="px-6 py-4 border-b border-gray-200">{user.email}</td>
              <td className="px-6 py-4 border-b border-gray-200">{user.role}</td>
              <td className="px-6 py-4 border-b border-gray-200">
                <Button
                  onClick={() => handleDelete(user.userId)}
                  className="bg-red-600 text-white hover:bg-red-700"
                >
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}