import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/constants.dart';
import 'package:rumahsehat_mobile/screens/home/home_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
          scaffoldBackgroundColor: kBackgroundColor,
          primarySwatch: Colors.red,
          textTheme: Theme.of(context).textTheme.apply(bodyColor: kTextColor)),
      title: 'RumahSehat App',
      home: const HomePage(),
    );
  }
}

// class RootPage extends StatefulWidget {
//   const RootPage({Key? key}) : super(key: key);

//   @override
//   State<RootPage> createState() => _RootPageState();
// }

// class _RootPageState extends State<RootPage> {
//   int currentPage = 0;
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       // appBar: AppBar(
//       //     backgroundColor: Colors.white,
//       //     title: const Text("Howdy!", style: TextStyle(color: Colors.black)),
//       //     centerTitle: false),
//       body: const HomePage(),
//       bottomNavigationBar: NavigationBar(
//         destinations: const [
//           NavigationDestination(icon: Icon(Icons.home), label: 'Home'),
//           NavigationDestination(
//               icon: Icon(Icons.menu_book), label: 'Appointment'),
//           NavigationDestination(icon: Icon(Icons.person), label: 'Profile')
//         ],
//         onDestinationSelected: (int index) {
//           setState(() {
//             currentPage = index;
//           });
//         },
//         selectedIndex: currentPage,
//       ),
//     );
//   }
// }
