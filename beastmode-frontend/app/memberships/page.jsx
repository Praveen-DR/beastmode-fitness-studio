'use client';
import { useEffect, useState } from 'react';
import { api } from '../../lib/api';
import { auth } from '../../utils/auth';
import Button from '../../components/Button';

export default function MembershipsPage() {
  const [memberships, setMemberships] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchMemberships = async () => {
      try {
        const userId = auth.getPayload().sub; // Get user ID from JWT payload
        const data = await api.fetch(`/memberships?userId=${userId}`);
        setMemberships(data);
      } catch (err) {
        setError(err.message || 'Failed to fetch memberships');
      } finally {
        setLoading(false);
      }
    };

    fetchMemberships();
  }, []);

  if (loading) {
    return <div className="text-center p-8">Loading memberships...</div>;
  }

  if (error) {
    return <div className="text-center p-8 text-red-500">{error}</div>;
  }

  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-6">Memberships</h1>
      <div className="overflow-x-auto">
        <table className="min-w-full bg-white rounded-lg shadow">
          <thead>
            <tr>
              <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
                Membership Name
              </th>
              <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
                Price
              </th>
              <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
                Duration (Months)
              </th>
              <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
                Status
              </th>
            </tr>
          </thead>
          <tbody>
            {memberships.map((membership) => (
              <tr key={membership.membershipId} className="hover:bg-gray-50">
                <td className="px-6 py-4 border-b border-gray-200">
                  {membership.membershipName}
                </td>
                <td className="px-6 py-4 border-b border-gray-200">
                  ${membership.price}
                </td>
                <td className="px-6 py-4 border-b border-gray-200">
                  {membership.durationInMonths}
                </td>
                <td className="px-6 py-4 border-b border-gray-200">
                  {membership.isActive ? 'Active' : 'Inactive'}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}