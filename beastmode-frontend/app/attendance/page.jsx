'use client';
import { useEffect, useState } from 'react';
import { api } from '../../lib/api';
import { auth } from '../../utils/auth';
import Button from '../../components/Button';

export default function AttendancePage() {
  const [attendanceRecords, setAttendanceRecords] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchAttendance = async () => {
      try {
        const userId = auth.getPayload().sub; // Get user ID from JWT payload
        const data = await api.fetch(`/attendance?userId=${userId}`);
        setAttendanceRecords(data);
      } catch (err) {
        setError(err.message || 'Failed to fetch attendance records');
      } finally {
        setLoading(false);
      }
    };

    fetchAttendance();
  }, []);

  if (loading) {
    return <div className="text-center p-8">Loading attendance records...</div>;
  }

  if (error) {
    return <div className="text-center p-8 text-red-500">{error}</div>;
  }

  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-6">Attendance Records</h1>
      <div className="overflow-x-auto">
        <table className="min-w-full bg-white rounded-lg shadow">
          <thead>
            <tr>
              <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
                Check-In Time
              </th>
              <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
                Check-Out Time
              </th>
              <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
                Type
              </th>
            </tr>
          </thead>
          <tbody>
            {attendanceRecords.map((record) => (
              <tr key={record.attendanceId} className="hover:bg-gray-50">
                <td className="px-6 py-4 border-b border-gray-200">
                  {new Date(record.checkInTime).toLocaleString()}
                </td>
                <td className="px-6 py-4 border-b border-gray-200">
                  {record.checkOutTime ? new Date(record.checkOutTime).toLocaleString() : 'N/A'}
                </td>
                <td className="px-6 py-4 border-b border-gray-200">
                  {record.attendanceType}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}