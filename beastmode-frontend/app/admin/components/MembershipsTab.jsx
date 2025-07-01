'use client';
import { useEffect, useState } from 'react';
import { api } from '../../../lib/api';
import Button from '../../../components/Button';
import Table from '../../../components/Table';

export default function MembershipsTab() {
  const [memberships, setMemberships] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchMemberships = async () => {
      try {
        const data = await api.fetchMemberships();
        setMemberships(data);
      } catch (err) {
        setError(err.message || 'Failed to fetch memberships');
      } finally {
        setLoading(false);
      }
    };

    fetchMemberships();
  }, []);

  const handleDelete = async (membershipId) => {
    try {
      await api.deleteMembership(membershipId);
      setMemberships(memberships.filter((m) => m.membershipId !== membershipId));
    } catch (err) {
      setError(err.message || 'Failed to delete membership');
    }
  };

  if (loading) {
    return <div className="text-center p-8">Loading memberships...</div>;
  }

  if (error) {
    return <div className="text-center p-8 text-red-500">{error}</div>;
  }

  return (
    <div className="space-y-6">
      <h2 className="text-xl font-bold">Memberships</h2>
      <Table
        headers={['Name', 'Price', 'Duration (Months)', 'Status', 'Actions']}
        data={memberships.map((membership) => [
          membership.membershipName,
          `$${membership.price}`,
          membership.durationInMonths,
          membership.isActive ? 'Active' : 'Inactive',
          <Button
            key={membership.membershipId}
            onClick={() => handleDelete(membership.membershipId)}
            className="bg-red-600 text-white hover:bg-red-700"
          >
            Delete
          </Button>,
        ])}
      />
    </div>
  );
}