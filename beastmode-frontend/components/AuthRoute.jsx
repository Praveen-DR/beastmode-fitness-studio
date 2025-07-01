// 'use client';
// import { useEffect } from 'react';
// import { useRouter } from 'next/navigation';
// import { auth } from '../utils/auth';

// export default function AuthRoute({ children, requiredRole }) {
//   const router = useRouter();
//   const userRole = auth.getUserRole();

//   useEffect(() => {
//     if (!auth.isAuthenticated()) {
//       router.push('/login');
//     } else if (requiredRole && userRole !== requiredRole) {
//       router.push('/dashboard');
//     }
//   }, [router, requiredRole, userRole]);

//   if (!auth.isAuthenticated() || (requiredRole && userRole !== requiredRole)) {
//     return null;
//   }

//   return children;
// }