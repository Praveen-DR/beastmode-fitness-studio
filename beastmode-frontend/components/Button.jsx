
'use client';

export default function Button({
  children,
  onClick,
  className = '',
  variant = 'primary',
  type = 'button',
  disabled = false,
}) {
  const baseClasses = 'px-6 py-2 rounded-md font-medium transition-all duration-200';
  
  const variants = {
    primary: 'bg-indigo-600 text-white hover:bg-indigo-700',
    secondary: 'bg-gray-200 text-gray-700 hover:bg-gray-300',
    danger: 'bg-red-600 text-white hover:bg-red-700',
  };

  return (
    <button
      type={type}
      onClick={onClick}
      disabled={disabled}
      className={`${baseClasses} ${variants[variant]} ${className}`}
    >
      {children}
    </button>
  );
}