class TagihanModel {
  final String kode;
  final bool status;
  final DateTime tanggalTerbuat;
  final int jumlahTagihan;

  const TagihanModel({
    required this.kode,
    required this.jumlahTagihan,
    required this.tanggalTerbuat,
    required this.status;
  });

  factory TagihanModel.fromJson(Map<String?, dynamic> json) {
    return TagihanModel(
      kode: json['kode'],
      jumlahTagihan: json['jumlahTagihan']
    );
  }
}