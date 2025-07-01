'use client';
import { useState, useEffect } from 'react';
import { api } from '../../../lib/api';
import Button from '../../../components/Button';

export default function UpdateMembershipForm({ membershipId, onSuccess }) {
  const [formData, setFormData] = useState({
    membershipName: '',
    price: 0,
    durationInMonths: 1,
    isActive: true,
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchMembership = async () => {
      try {
        const data = await api.getMembershipById(membershipId);
        setFormData({
          membershipName: data.membershipName,
          price: data.price,
          durationInMonths: data.durationInMonths,
          isActive: data.isActive,
        });
      } catch (err) {
        setError(err.message || 'Failed to fetch membership');
      }
    };

    fetchMembership();
  }, [membershipId]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      await api.updateMembership({ ...formData, membershipId });
      onSuccess(); // Refresh the membership list
    } catch (err) {
      setError(err.message || 'Failed to update membership');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="space-y-4">
      <h2 className="text-xl font-bold">Update Membership</h2>
      {error && <div className="p-3 bg-red-100 text-red-700 rounded">{error}</div>}
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block text-sm font-medium mb-1">Membership Name</label>
          <input
            type="text"
            value={formData.membershipName}
            onChange={(e) => setFormData({ ...formData, membershipName: e.target.value })}
            className="w-full px-3 py-2 border rounded-md"
            required
          />
        </div>

        <div>
          <label className="block text-sm font-medium mb-1">Price</label>
          <input
            type="number"
            value={formData.price}
            onChange={(e) => setFormData({ ...formData, price: parseFloat(e.target.value) })}
            className="w-full px-3 py-2 border rounded-md"
            required
          />
        </div>

        <div>
          <label className="block text-sm font-medium mb-1">Duration (Months)</label>
          <input
            type="number"
            value={formData.durationInMonths}
            onChange={(e) => setFormData({ ...formData, durationInMonths: parseInt(e.target.value) })}
            className="w-full px-3 py-2 border rounded-md"
            required
          />
        </div>

        <div>
          <label className="block text-sm font-medium mb-1">Status</label>
          <select
            value={formData.isActive}
            onChange={(e) => setFormData({ ...formData, isActive: e.target.value === 'true' })}
            className="w-full px-3 py-2 border rounded-md"
          >
            <option value={true}>Active</option>
            <option value={false}>Inactive</option>
          </select>
        </div>

        <Button type="submit" className="w-full" disabled={loading}>
          {loading ? 'Updating...' : 'Update Membership'}
        </Button>
      </form>
    </div>
  );
}