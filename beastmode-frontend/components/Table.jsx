'use client';

export default function Table({ headers, data }) {
  return (
    <div className="overflow-x-auto">
      <table className="min-w-full bg-white rounded-lg shadow">
        <thead>
          <tr>
            {headers.map((header, index) => (
              <th
                key={index}
                className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600"
              >
                {header}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {data.map((row, rowIndex) => (
            <tr key={rowIndex} className="hover:bg-gray-50">
              {row.map((cell, cellIndex) => (
                <td
                  key={cellIndex}
                  className="px-6 py-4 border-b border-gray-200"
                >
                  {cell}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}