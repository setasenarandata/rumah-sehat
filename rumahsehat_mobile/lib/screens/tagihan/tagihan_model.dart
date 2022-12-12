class TagihanModel {
  final String kode;
  final bool isPaid;
  final String tanggalTerbuat;
  final int jumlahTagihan;

  const TagihanModel({
    required this.kode,
    required this.isPaid,
    required this.tanggalTerbuat,
    required this.jumlahTagihan,
  });

  factory TagihanModel.fromJson(Map<String?, dynamic> json) {
    return TagihanModel(
      kode: json['kode'],
      isPaid: json['isPaid'],
      tanggalTerbuat: json['tanggalTerbuat'],
      jumlahTagihan: json['jumlahTagihan']
    );
  }

  // String getStatus() {
  //   if (this.isPaid) {
  //     return "Lunas";
  //   } else {
  //     return "Belum Lunas";
  //   }
  // }
}