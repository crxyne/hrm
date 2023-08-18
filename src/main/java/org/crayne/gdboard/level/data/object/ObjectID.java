package org.crayne.gdboard.level.data.object;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.decoration.ColorableObject;
import org.crayne.gdboard.level.data.object.type.decoration.PulsatingObject;
import org.crayne.gdboard.level.data.object.type.decoration.RotatingObject;
import org.crayne.gdboard.level.data.object.type.decoration.TextObject;
import org.crayne.gdboard.level.data.object.type.general.OrbObject;
import org.crayne.gdboard.level.data.object.type.general.ToggleOrbObject;
import org.crayne.gdboard.level.data.object.type.portal.SpecialPortalObject;
import org.crayne.gdboard.level.data.object.type.portal.TeleportPortalObject;
import org.crayne.gdboard.level.data.object.type.trigger.collision.CollisionBlockObject;
import org.crayne.gdboard.level.data.object.type.trigger.collision.CollisionTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.general.SpawnTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.general.StartPositionTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.general.StopTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.general.TouchTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.item.count.CountTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.item.count.InstantCountTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.item.count.ItemCounter;
import org.crayne.gdboard.level.data.object.type.trigger.item.pickup.PickupItemObject;
import org.crayne.gdboard.level.data.object.type.trigger.item.pickup.PickupTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.movement.FollowPlayerYTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.movement.FollowTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.movement.MoveTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.movement.RotateTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.toggle.ToggleTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.visual.AlphaTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.visual.AnimateTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.visual.ShakeTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.visual.color.ColorTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.visual.color.PulseTrigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ObjectID {

    private ObjectID() {}

    // sorry, but performance is more important than "clean code"
    @NotNull
    public static LevelObject parse(@NotNull final Properties properties) {
        final int objectID = properties.integerProperty(LevelObjectProperty.OBJECT_ID);
        return switch (objectID) {
            case 1839, 1840, 1841, 1842 -> new PulsatingObject(properties);
            case 85, 86, 87, 97, 137, 138, 139, 154, 155, 156,
                    180, 181, 182, 183, 184, 185, 186, 187, 188,
                    222, 223, 224, 375, 376, 377, 378, 394, 395,
                    396, 678, 679, 680, 740, 741, 742, 997, 998,
                    999, 1000, 1019, 1020, 1021, 1055, 1056, 1057,
                    1058, 1059, 1060, 1061, 1521, 1522, 1523, 1524,
                    1525, 1526, 1527, 1528, 1582, 1619, 1620, 1705,
                    1706, 1707, 1708, 1709, 1710, 1734, 1735, 1736,
                    1752, 1831, 1832, 1833, 1834 -> new RotatingObject(properties);
            case 914 -> new TextObject(properties);
            case 1615 -> new ItemCounter(properties);
            case 31 -> new StartPositionTrigger(properties);
            case 899 -> new ColorTrigger(properties);
            case 901 -> new MoveTrigger(properties);
            case 1006 -> new PulseTrigger(properties);
            case 1594 -> new ToggleOrbObject(properties);
            case 36, 84, 141, 1022, 1330, 1333, 1704, 1751 -> new OrbObject(properties);
            case 13, 47, 111, 200, 201, 202, 203, 660, 1331, 1334 -> new SpecialPortalObject(properties);
            case 747 -> new TeleportPortalObject(properties);
            case 1816 -> new CollisionBlockObject(properties);
            case 1275, 1587, 1589, 1598, 1614 -> new PickupItemObject(properties);
            case 1007 -> new AlphaTrigger(properties);
            case 1049, 1812 -> new ToggleTrigger(properties);
            case 1268 -> new SpawnTrigger(properties);
            case 1346 -> new RotateTrigger(properties);
            case 1347 -> new FollowTrigger(properties);
            case 1520 -> new ShakeTrigger(properties);
            case 1585 -> new AnimateTrigger(properties);
            case 1595 -> new TouchTrigger(properties);
            case 1611 -> new CountTrigger(properties);
            case 1616 -> new StopTrigger(properties);
            case 1811 -> new InstantCountTrigger(properties);
            case 1814 -> new FollowPlayerYTrigger(properties);
            case 1815 -> new CollisionTrigger(properties);
            case 1817 -> new PickupTrigger(properties);
            case 1, 2, 3, 4, 5, 6, 7, 8, 15, 16, 17, 39, 62, 63, 64, 65, 66, 68, 69,
                    70, 71, 72, 73, 74, 75, 76, 77, 78, 80, 81, 82, 83, 90, 91, 92, 93, 94,
                    95, 96, 103, 116, 117, 118, 119, 120, 121, 122, 143, 144, 145, 146, 147,
                    170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 192, 194, 197, 204, 205,
                    206, 245, 246, 294, 295, 296, 297, 305, 307, 324, 325, 326, 327, 328, 329,
                    358, 371, 372, 373, 374, 392, 459, 467, 468, 469, 470, 471, 472, 473, 474,
                    475, 476, 502, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526,
                    527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541,
                    542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556,
                    557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571,
                    572, 573, 574, 575, 576, 577, 623, 624, 625, 626, 627, 628, 629, 630, 631,
                    632, 633, 634, 635, 636, 637, 638, 639, 640, 650, 653, 654, 655, 656, 657,
                    658, 659, 661, 662, 663, 664, 668, 669, 670, 671, 672, 673, 674, 681, 682,
                    683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 713, 714, 715, 716, 722,
                    723, 724, 730, 731, 732, 733, 734, 735, 736, 738, 755, 766, 775, 823, 824,
                    905, 911, 943, 944, 945, 946, 947, 948, 949, 950, 951, 955, 966, 973, 976,
                    977, 1024, 1025, 1026, 1027, 1028, 1029, 1030, 1031, 1032, 1041, 1042, 1043,
                    1044, 1045, 1046, 1047, 1048, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070,
                    1071, 1090, 1140, 1141, 1142, 1143, 1144, 1145, 1146, 1147, 1148, 1149, 1150,
                    1151, 1152, 1153, 1154, 1155, 1156, 1157, 1158, 1186, 1191, 1192, 1193, 1194,
                    1195, 1196, 1197, 1198, 1199, 1200, 1201, 1202, 1203, 1204, 1205, 1206, 1207,
                    1208, 1209, 1210, 1220, 1221, 1222, 1223, 1224, 1225, 1226, 1227, 1260,
                    1261, 1262, 1263, 1264, 1265, 1278, 1280, 1309, 1310, 1311, 1312, 1313,
                    1314, 1315, 1316, 1317, 1318, 1319, 1320, 1338, 1339, 1340, 1341, 1342,
                    1343, 1344, 1345, 1366, 1441, 1449, 1450, 1451, 1452, 1529, 1530, 1531,
                    1532, 1533, 1534, 1535, 1536, 1537, 1538, 1539, 1540, 1622, 1625, 1626,
                    1628, 1633, 1634, 1635, 1636, 1639, 1640, 1643, 1644, 1647, 1648, 1651,
                    1652, 1711, 1712, 1713, 1714, 1737, 1738, 1739, 1740, 1741, 1742, 1743,
                    1744, 1745, 1746, 1747, 1748, 1749, 1750, 1755, 1756, 1769, 1770, 1771,
                    1772, 1776, 1777, 1778, 1779, 1780, 1785, 1786, 1787, 1788, 1795, 1798,
                    1813, 1829, 1835, 1836, 1837, 1838, 1859, 207, 208, 209, 210, 212, 213,
                    215, 216, 217, 218, 219, 220, 255, 256, 257, 258, 260, 261, 263, 264, 265,
                    267, 268, 269, 270, 271, 272, 274, 275, 409, 410, 411, 412, 413, 453, 454,
                    455, 456, 457, 458, 477, 478, 479, 480, 481, 482, 485, 486, 487, 488, 489,
                    490, 491, 641, 642, 643, 644, 645, 646, 647, 648, 649, 703,
                    704, 705, 706, 707, 708, 739, 752, 753, 754, 756, 757, 758, 759, 762, 763,
                    764, 765, 769, 770, 771, 772, 773, 774, 807, 808, 809, 810, 811, 812, 813,
                    814, 815, 816, 817, 818, 819, 820, 821, 822, 825, 826, 827, 828, 829, 830,
                    831, 832, 833, 841, 842, 843, 844, 845, 846, 847, 848, 850, 853, 854, 855,
                    856, 857, 859, 861, 862, 863, 867, 868, 869, 870, 871, 872, 873, 874, 877,
                    878, 880, 881, 882, 883, 884, 885, 888, 889, 890, 891, 893, 894, 895, 896,
                    903, 904, 918, 920, 921, 923, 924, 939, 952, 953, 954, 956, 957, 958, 959,
                    960, 961, 964, 965, 967, 968, 969, 970, 971, 972, 974, 975, 980, 981, 982,
                    983, 984, 985, 986, 987, 988, 1001, 1002, 1003, 1004, 1005, 1033, 1034, 1035,
                    1036, 1037, 1038, 1039, 1040, 1053, 1054, 1062, 1075, 1076, 1077, 1078, 1079,
                    1080, 1081, 1082, 1083, 1084, 1085, 1086, 1087, 1088, 1089, 1091, 1092, 1093,
                    1094, 1095, 1096, 1097, 1098, 1159, 1160, 1161, 1162, 1163, 1164, 1165, 1166,
                    1167, 1168, 1169, 1170, 1171, 1172, 1173, 1174, 1175, 1176, 1177, 1178, 1179,
                    1180, 1181, 1182, 1183, 1184, 1185, 1187, 1188, 1189, 1190, 1247, 1248, 1249,
                    1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258, 1259, 1266, 1267,
                    1276, 1277, 1279, 1281, 1282, 1283, 1284, 1285, 1286, 1287, 1288, 1289, 1290,
                    1294, 1295, 1296, 1297, 1298, 1299, 1300, 1301, 1302, 1303, 1304, 1305, 1306,
                    1307, 1308, 1322, 1325, 1326, 1327, 1328, 1348, 1349, 1350, 1351, 1352, 1353,
                    1354, 1355, 1356, 1357, 1358, 1359, 1360, 1361, 1362, 1363, 1364, 1365, 1387,
                    1388, 1389, 1390, 1391, 1392, 1393, 1394, 1395, 1431, 1432, 1433, 1434, 1435,
                    1436, 1437, 1438, 1439, 1440, 1442, 1443, 1444, 1445, 1446, 1447, 1448, 1461,
                    1462, 1463, 1464, 1471, 1472, 1473, 1496, 1507, 1510, 1511, 1512, 1513, 1514,
                    1515, 1516, 1517, 1552, 1553, 1554, 1555, 1556, 1557, 1558, 1559, 1560,
                    1583, 1584, 1586, 1588, 1590, 1591, 1592, 1593, 1599,
                    1600, 1601, 1617, 1621, 1623, 1624, 1627, 1629, 1630, 1631,
                    1632, 1637, 1638, 1641, 1642, 1645, 1646, 1649, 1650, 1685, 1686, 1687, 1688,
                    1689, 1690, 1691, 1692, 1693, 1694, 1695, 1696, 1697, 1698, 1699, 1700, 1701,
                    1702, 1703, 1773, 1774, 1775, 1781, 1782,
                    1783, 1784, 1789, 1790, 1791, 1792, 1793, 1794, 1796, 1797, 1799, 1800, 1801,
                    1802, 1803, 1804, 1805, 1806, 1807, 1808, 1809, 1810, 1843, 1849, 1850, 1851,
                    1852, 1853, 1854, 1855, 1856, 1857, 1860, 1861, 1862, 1863, 1864, 1865, 1866,
                    1867, 1868, 1869, 1870, 1871, 1872, 1873, 1874, 1875, 1876, 1877, 1878, 1879,
                    1880, 1881, 1882, 1883, 1884, 1885, 1903, 1904, 1905, 1906, 1907, 1908, 1909,
                    1910, 1911, 18, 19, 20, 21, 41, 48, 49, 50, 51, 52, 53, 54, 60, 106, 107,
                    110, 113, 114, 115, 123, 124, 125, 126, 127, 128, 129, 130, 131,
                    132, 133, 134, 135, 136, 148, 149, 150, 151, 152, 153,  157, 158, 159, 190,
                    211, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238,
                    239, 240, 241, 242, 259, 266, 273, 277, 278, 279, 280, 281, 282, 283, 284, 285,
                    405, 406, 407, 408, 414, 419, 420, 448, 449, 450, 451,
                    452, 460, 494, 495, 496, 497, 498, 499, 500, 501, 503, 504, 505, 506, 507,
                    508, 509, 510, 511, 512, 513, 514, 578, 579, 580, 581, 582, 583, 584, 585,
                    586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600,
                    601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 616,
                    617, 618, 619, 620, 621, 622, 693, 694, 695, 696, 697, 698, 699, 700, 701,
                    702, 719, 721, 767, 902, 906, 907, 908, 909, 910,  916, 917, 919, 925,
                    926, 927, 928, 929, 930, 931, 932, 933, 934, 935, 936, 937, 938, 940, 941,
                    942, 990, 992, 1009, 1010, 1011, 1012, 1013, 1014, 1015,
                    1016, 1017, 1018, 1050, 1051, 1052,  1099, 1100, 1101, 1102, 1103, 1104, 1105, 1106, 1107, 1108,
                    1109, 1110, 1111, 1112, 1113, 1114, 1115, 1116, 1117, 1118, 1120, 1122, 1123,
                    1124, 1125, 1126, 1127, 1132, 1133, 1134, 1135, 1136, 1137, 1138, 1139, 1228,
                    1229, 1230, 1231, 1232, 1233, 1234, 1235, 1236, 1237, 1238, 1239, 1240, 1241,
                    1242, 1243, 1244, 1245, 1246, 1269, 1270, 1271, 1272, 1273, 1274, 1291, 1292,
                    1293, 1367, 1368, 1369, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378,
                    1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386, 1453, 1454, 1455, 1456, 1457,
                    1458, 1459, 1460, 1518, 1519,
                    1596, 1597, 1602, 1603, 1604, 1605, 1606, 1607, 1608, 1609, 1610,  1618,
                    1653, 1654, 1655, 1656, 1657, 1658, 1659, 1660, 1661, 1662, 1663, 1664, 1665,
                    1666, 1667, 1668, 1669, 1670, 1671, 1672, 1673, 1674, 1675, 1676, 1677, 1678,
                    1679, 1680, 1681, 1682, 1683, 1684, 1715, 1716, 1717, 1718,
                    1719, 1720, 1721, 1722, 1723, 1724, 1725, 1726, 1727, 1728, 1729, 1730, 1731,
                    1732, 1733, 1753, 1754, 1757, 1758, 1759, 1760, 1761, 1762, 1763, 1764,
                    1765, 1766, 1767, 1768, 1820, 1821, 1823, 1824, 1825, 1826, 1827, 1828, 1830,
                    1844, 1845, 1846, 1847, 1848,
                    1858, 1886, 1887, 1888, 1889, 1890, 1891, 1892, 1893, 1894, 1895, 1896, 1897,
                    1898, 1899, 1900, 1901, 1902 -> new ColorableObject(properties);
            default -> new LevelObject(properties);
        };
    }

    public static boolean isBaseColorObject(final int objectID) {
        return switch (objectID) {
            case 1, 2, 3, 4, 5, 6, 7, 8, 15, 16, 17, 39, 62, 63, 64, 65, 66, 68, 69,
                    70, 71, 72, 73, 74, 75, 76, 77, 78, 80, 81, 82, 83, 90, 91, 92, 93, 94,
                    95, 96, 103, 116, 117, 118, 119, 120, 121, 122, 143, 144, 145, 146, 147,
                    170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 183, 184, 185, 186, 187,
                    188, 192, 194, 197, 204, 205, 206, 245, 246, 294, 295, 296, 297, 305, 307,
                    324, 325, 326, 327, 328, 329, 358, 371, 372, 373, 374, 392, 459, 467, 468,
                    469, 470, 471, 472, 473, 474, 475, 476, 502, 515, 516, 517, 518, 519, 520,
                    521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535,
                    536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550,
                    551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565,
                    566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 623, 624, 625,
                    626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640,
                    650, 653, 654, 655, 656, 657, 658, 659, 661, 662, 663, 664, 668, 669, 670,
                    671, 672, 673, 674, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691,
                    692, 713, 714, 715, 716, 722, 723, 724, 730, 731, 732, 733, 734, 735, 736,
                    738, 740, 741, 742, 755, 766, 775, 823, 824, 905, 911, 943, 944, 945, 946,
                    947, 948, 949, 950, 951, 955, 966, 973, 976, 977, 1024, 1025, 1026, 1027,
                    1028, 1029, 1030, 1031, 1032, 1041, 1042, 1043, 1044, 1045, 1046, 1047,
                    1048, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070, 1071, 1090, 1140,
                    1141, 1142, 1143, 1144, 1145, 1146, 1147, 1148, 1149, 1150, 1151, 1152,
                    1153, 1154, 1155, 1156, 1157, 1158, 1186, 1191, 1192, 1193, 1194, 1195,
                    1196, 1197, 1198, 1199, 1200, 1201, 1202, 1203, 1204, 1205, 1206, 1207,
                    1208, 1209, 1210, 1220, 1221, 1222, 1223, 1224, 1225, 1226, 1227, 1260,
                    1261, 1262, 1263, 1264, 1265, 1278, 1280, 1309, 1310, 1311, 1312, 1313,
                    1314, 1315, 1316, 1317, 1318, 1319, 1320, 1338, 1339, 1340, 1341, 1342,
                    1343, 1344, 1345, 1366, 1441, 1449, 1450, 1451, 1452, 1529, 1530, 1531,
                    1532, 1533, 1534, 1535, 1536, 1537, 1538, 1539, 1540, 1622, 1625, 1626,
                    1628, 1633, 1634, 1635, 1636, 1639, 1640, 1643, 1644, 1647, 1648, 1651,
                    1652, 1711, 1712, 1713, 1714, 1737, 1738, 1739, 1740, 1741, 1742, 1743,
                    1744, 1745, 1746, 1747, 1748, 1749, 1750, 1755, 1756, 1769, 1770, 1771,
                    1772, 1776, 1777, 1778, 1779, 1780, 1785, 1786, 1787, 1788, 1795, 1798,
                    1813, 1829, 1835, 1836, 1837, 1838, 1859 -> true;
            default -> false;
        };
    }

    public static boolean isBaseAndDetailColorObject(final int objectID) {
        return switch (objectID) {
            case 207, 208, 209, 210, 212, 213, 215, 216, 217, 218, 219, 220, 255, 256, 257,
                    258, 260, 261, 263, 264, 265, 267, 268, 269, 270, 271, 272, 274, 275, 409,
                    410, 411, 412, 413, 453, 454, 455, 456, 457, 458, 477, 478, 479, 480, 481,
                    482, 485, 486, 487, 488, 489, 490, 491, 641, 642, 643, 644, 645, 646, 647,
                    648, 649, 678, 679, 680, 703, 704, 705, 706, 707, 708, 739, 752, 753, 754,
                    756, 757, 758, 759, 762, 763, 764, 765, 769, 770, 771, 772, 773, 774, 807,
                    808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822,
                    825, 826, 827, 828, 829, 830, 831, 832, 833, 841, 842, 843, 844, 845, 846,
                    847, 848, 850, 853, 854, 855, 856, 857, 859, 861, 862, 863, 867, 868, 869,
                    870, 871, 872, 873, 874, 877, 878, 880, 881, 882, 883, 884, 885, 888, 889,
                    890, 891, 893, 894, 895, 896, 903, 904, 918, 920, 921, 923, 924, 939, 952,
                    953, 954, 956, 957, 958, 959, 960, 961, 964, 965, 967, 968, 969, 970, 971,
                    972, 974, 975, 980, 981, 982, 983, 984, 985, 986, 987, 988, 1001, 1002, 1003,
                    1004, 1005, 1033, 1034, 1035, 1036, 1037, 1038, 1039, 1040, 1053, 1054, 1062,
                    1075, 1076, 1077, 1078, 1079, 1080, 1081, 1082, 1083, 1084, 1085, 1086, 1087,
                    1088, 1089, 1091, 1092, 1093, 1094, 1095, 1096, 1097, 1098, 1159, 1160, 1161,
                    1162, 1163, 1164, 1165, 1166, 1167, 1168, 1169, 1170, 1171, 1172, 1173, 1174,
                    1175, 1176, 1177, 1178, 1179, 1180, 1181, 1182, 1183, 1184, 1185, 1187, 1188,
                    1189, 1190, 1247, 1248, 1249, 1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257,
                    1258, 1259, 1266, 1267, 1275, 1276, 1277, 1279, 1281, 1282, 1283, 1284, 1285,
                    1286, 1287, 1288, 1289, 1290, 1294, 1295, 1296, 1297, 1298, 1299, 1300, 1301,
                    1302, 1303, 1304, 1305, 1306, 1307, 1308, 1322, 1325, 1326, 1327, 1328, 1348,
                    1349, 1350, 1351, 1352, 1353, 1354, 1355, 1356, 1357, 1358, 1359, 1360, 1361,
                    1362, 1363, 1364, 1365, 1387, 1388, 1389, 1390, 1391, 1392, 1393, 1394, 1395,
                    1431, 1432, 1433, 1434, 1435, 1436, 1437, 1438, 1439, 1440, 1442, 1443, 1444,
                    1445, 1446, 1447, 1448, 1461, 1462, 1463, 1464, 1471, 1472, 1473, 1496, 1507,
                    1510, 1511, 1512, 1513, 1514, 1515, 1516, 1517, 1552, 1553, 1554, 1555, 1556,
                    1557, 1558, 1559, 1560, 1582, 1583, 1584, 1586, 1587, 1588, 1589, 1590, 1591,
                    1592, 1593, 1594, 1598, 1599, 1600, 1601, 1614, 1617, 1619, 1620, 1621, 1623,
                    1624, 1627, 1629, 1630, 1631, 1632, 1637, 1638, 1641, 1642, 1645, 1646, 1649,
                    1650, 1685, 1686, 1687, 1688, 1689, 1690, 1691, 1692, 1693, 1694, 1695, 1696,
                    1697, 1698, 1699, 1700, 1701, 1702, 1703, 1708, 1709, 1710, 1734, 1735, 1736,
                    1773, 1774, 1775, 1781, 1782, 1783, 1784, 1789, 1790, 1791, 1792, 1793, 1794,
                    1796, 1797, 1799, 1800, 1801, 1802, 1803, 1804, 1805, 1806, 1807, 1808, 1809,
                    1810, 1843, 1849, 1850, 1851, 1852, 1853, 1854, 1855, 1856, 1857, 1860, 1861,
                    1862, 1863, 1864, 1865, 1866, 1867, 1868, 1869, 1870, 1871, 1872, 1873, 1874,
                    1875, 1876, 1877, 1878, 1879, 1880, 1881, 1882, 1883, 1884, 1885, 1903, 1904,
                    1905, 1906, 1907, 1908, 1909, 1910, 1911 -> true;
            default -> false;
        };
    }

    public static boolean isDetailColorObject(final int objectID) {
        return switch (objectID) {
            case 0, 18, 19, 20, 21, 41, 48, 49, 50, 51, 52, 53, 54, 60, 85, 86, 87, 97, 106,
                    107, 110, 113, 114, 115, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132,
                    133, 134, 135, 136, 137, 138, 139, 148, 149, 150, 151, 152, 153, 154, 155,
                    156, 157, 158, 159, 180, 181, 182, 190, 211, 222, 223, 224, 225, 226, 227,
                    228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242,
                    259, 266, 273, 277, 278, 279, 280, 281, 282, 283, 284, 285, 375, 376, 377,
                    378, 394, 395, 396, 405, 406, 407, 408, 414, 419, 420, 448, 449, 450, 451,
                    452, 460, 494, 495, 496, 497, 498, 499, 500, 501, 503, 504, 505, 506, 507,
                    508, 509, 510, 511, 512, 513, 514, 578, 579, 580, 581, 582, 583, 584, 585,
                    586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600,
                    601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 616,
                    617, 618, 619, 620, 621, 622, 693, 694, 695, 696, 697, 698, 699, 700, 701,
                    702, 719, 721, 767, 902, 906, 907, 908, 909, 910, 914, 916, 917, 919, 925,
                    926, 927, 928, 929, 930, 931, 932, 933, 934, 935, 936, 937, 938, 940, 941,
                    942, 990, 992, 997, 998, 999, 1000, 1009, 1010, 1011, 1012, 1013, 1014, 1015,
                    1016, 1017, 1018, 1019, 1020, 1021, 1050, 1051, 1052, 1055, 1056, 1057, 1058,
                    1059, 1060, 1061, 1099, 1100, 1101, 1102, 1103, 1104, 1105, 1106, 1107, 1108,
                    1109, 1110, 1111, 1112, 1113, 1114, 1115, 1116, 1117, 1118, 1120, 1122, 1123,
                    1124, 1125, 1126, 1127, 1132, 1133, 1134, 1135, 1136, 1137, 1138, 1139, 1228,
                    1229, 1230, 1231, 1232, 1233, 1234, 1235, 1236, 1237, 1238, 1239, 1240, 1241,
                    1242, 1243, 1244, 1245, 1246, 1269, 1270, 1271, 1272, 1273, 1274, 1291, 1292,
                    1293, 1367, 1368, 1369, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378,
                    1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386, 1453, 1454, 1455, 1456, 1457,
                    1458, 1459, 1460, 1518, 1519, 1521, 1522, 1523, 1524, 1525, 1526, 1527, 1528,
                    1596, 1597, 1602, 1603, 1604, 1605, 1606, 1607, 1608, 1609, 1610, 1615, 1618,
                    1653, 1654, 1655, 1656, 1657, 1658, 1659, 1660, 1661, 1662, 1663, 1664, 1665,
                    1666, 1667, 1668, 1669, 1670, 1671, 1672, 1673, 1674, 1675, 1676, 1677, 1678,
                    1679, 1680, 1681, 1682, 1683, 1684, 1705, 1706, 1707, 1715, 1716, 1717, 1718,
                    1719, 1720, 1721, 1722, 1723, 1724, 1725, 1726, 1727, 1728, 1729, 1730, 1731,
                    1732, 1733, 1752, 1753, 1754, 1757, 1758, 1759, 1760, 1761, 1762, 1763, 1764,
                    1765, 1766, 1767, 1768, 1820, 1821, 1823, 1824, 1825, 1826, 1827, 1828, 1830,
                    1831, 1832, 1833, 1834, 1839, 1840, 1841, 1842, 1844, 1845, 1846, 1847, 1848,
                    1858, 1886, 1887, 1888, 1889, 1890, 1891, 1892, 1893, 1894, 1895, 1896, 1897,
                    1898, 1899, 1900, 1901, 1902 -> true;
            default -> false;
        };
    }

    @Nullable
    public static String spriteForObjectID(final int objectID) {
        return switch (objectID) {
            case 1 -> "square_01_001.png";
            case 2 -> "square_02_001.png";
            case 3 -> "square_03_001.png";
            case 4 -> "square_04_001.png";
            case 5 -> "square_05_001.png";
            case 6 -> "square_06_001.png";
            case 7 -> "square_07_001.png";
            case 8 -> "spike_01_001.png";
            case 9 -> "pit_01_001.png";
            case 10 -> "portal_01_front_001.png";
            case 11 -> "portal_02_front_001.png";
            case 12 -> "portal_03_front_001.png";
            case 13 -> "portal_04_front_001.png";
            case 15 -> "rod_01_001.png";
            case 16 -> "rod_02_001.png";
            case 17 -> "rod_03_001.png";
            case 18 -> "d_spikes_01_001.png";
            case 19 -> "d_spikes_02_001.png";
            case 20 -> "d_spikes_03_001.png";
            case 21 -> "d_spikes_04_001.png";
            case 22 -> "edit_eeNoneBtn_001.png";
            case 23 -> "edit_eeFBBtn_001.png";
            case 24 -> "edit_eeFTBtn_001.png";
            case 25 -> "edit_eeFLBtn_001.png";
            case 26 -> "edit_eeFRBtn_001.png";
            case 27 -> "edit_eeSUBtn_001.png";
            case 28 -> "edit_eeSDBtn_001.png";
            case 29 -> "edit_eTintBGBtn_001.png";
            case 30 -> "edit_eTintGBtn_001.png";
            case 31 -> "edit_eStartPosBtn_001.png";
            case 32 -> "edit_eGhostEBtn_001.png";
            case 33 -> "edit_eGhostDBtn_001.png";
            case 34 -> "edit_eStartPosBtn_001.png";
            case 35 -> "bump_01_001.png";
            case 36 -> "ring_01_001.png";
            case 39 -> "spike_02_001.png";
            case 40 -> "plank_01_001.png";
            case 41 -> "chain_01_001.png";
            case 45 -> "portal_05_front_001.png";
            case 46 -> "portal_06_front_001.png";
            case 47 -> "portal_07_front_001.png";
            case 48 -> "d_cloud_01_001.png";
            case 49 -> "d_cloud_02_001.png";
            case 50 -> "d_ball_01_001.png";
            case 51 -> "d_ball_02_001.png";
            case 52 -> "d_ball_03_001.png";
            case 53 -> "d_ball_04_001.png";
            case 54 -> "d_ball_05_001.png";
            case 55 -> "edit_eeFABtn_001.png";
            case 56 -> "edit_eeFALBtn_001.png";
            case 57 -> "edit_eeFARBtn_001.png";
            case 58 -> "edit_eeFRHBtn_001.png";
            case 59 -> "edit_eeFRHInvBtn_001.png";
            case 60 -> "d_ball_06_001.png";
            case 61 -> "pit_04_001.png";
            case 62 -> "square_b_01_001.png";
            case 63 -> "square_b_02_001.png";
            case 64 -> "square_b_03_001.png";
            case 65 -> "square_b_04_001.png";
            case 66 -> "square_b_05_001.png";
            case 67 -> "gravbump_01_001.png";
            case 68 -> "square_b_06_001.png";
            case 69 -> "blockOutline_01_001.png";
            case 70 -> "lightsquare_01_02_001.png";
            case 71 -> "blockOutline_03_001.png";
            case 72 -> "blockOutline_06_001.png";
            case 73 -> "square_c_05_001.png";
            case 74 -> "blockOutline_04_001.png";
            case 75 -> "blockOutline_05_001.png";
            case 76 -> "lightsquare_04_02_001.png";
            case 77 -> "lightsquare_04_02_001.png";
            case 78 -> "lightsquare_04_02_001.png";
            case 80 -> "square_d_05_001.png";
            case 81 -> "lightsquare_04_02_001.png";
            case 82 -> "lightsquare_04_sideLine_001.png";
            case 83 -> "square_08_001.png";
            case 84 -> "gravring_01_001.png";
            case 85 -> "d_cogwheel_01_001.png";
            case 86 -> "d_cogwheel_02_001.png";
            case 87 -> "d_cogwheel_03_001.png";
            case 88 -> "sawblade_01_001.png";
            case 89 -> "sawblade_02_001.png";
            case 90 -> "blockOutline_01_001.png";
            case 91 -> "lightsquare_01_02_001.png";
            case 92 -> "blockOutline_03_001.png";
            case 93 -> "blockOutline_06_001.png";
            case 94 -> "lightsquare_01_05_color_001.png";
            case 95 -> "blockOutline_04_001.png";
            case 96 -> "blockOutline_05_001.png";
            case 97 -> "d_cogwheel_04_001.png";
            case 98 -> "sawblade_03_001.png";
            case 99 -> "portal_08_front_001.png";
            case 101 -> "portal_09_front_001.png";
            case 103 -> "spike_03_001.png";
            case 105 -> "edit_eTintObjBtn_001.png";
            case 106 -> "d_02_chain_01_001.png";
            case 107 -> "d_02_chain_02_001.png";
            case 110 -> "d_chain_02_001.png";
            case 111 -> "portal_10_front_001.png";
            case 113 -> "d_brick_01_001.png";
            case 114 -> "d_brick_02_001.png";
            case 115 -> "d_brick_03_001.png";
            case 116 -> "square_f_01_001.png";
            case 117 -> "square_f_02_001.png";
            case 118 -> "square_f_03_001.png";
            case 119 -> "blockOutline_06_001.png";
            case 120 -> "square_f_05_001.png";
            case 121 -> "square_f_06_001.png";
            case 122 -> "square_f_07_001.png";
            case 123 -> "d_thorn_01_001.png";
            case 124 -> "d_thorn_02_001.png";
            case 125 -> "d_thorn_03_001.png";
            case 126 -> "d_thorn_04_001.png";
            case 127 -> "d_thorn_05_001.png";
            case 128 -> "d_thorn_06_001.png";
            case 129 -> "d_cloud_03_001.png";
            case 130 -> "d_cloud_04_001.png";
            case 131 -> "d_cloud_05_001.png";
            case 132 -> "d_arrow_01_001.png";
            case 133 -> "d_exmark_01_001.png";
            case 134 -> "d_art_01_001.png";
            case 135 -> "pit_b_01_001.png";
            case 136 -> "d_qmark_01_001.png";
            case 137 -> "d_wheel_01_001.png";
            case 138 -> "d_wheel_02_001.png";
            case 139 -> "d_wheel_03_001.png";
            case 140 -> "bump_03_001.png";
            case 141 -> "ring_03_001.png";
            case 142 -> "secretCoin_01_001.png";
            case 143 -> "brick_02_001.png";
            case 144 -> "invis_spike_01_001.png";
            case 145 -> "invis_spike_03_001.png";
            case 146 -> "invis_square_01_001.png";
            case 147 -> "invis_plank_01_001.png";
            case 148 -> "d_ball_07_001.png";
            case 149 -> "d_ball_08_001.png";
            case 150 -> "d_cross_01_001.png";
            case 151 -> "d_spikeart_01_001.png";
            case 152 -> "d_spikeart_02_001.png";
            case 153 -> "d_spikeart_03_001.png";
            case 154 -> "d_spikewheel_01_001.png";
            case 155 -> "d_spikewheel_02_001.png";
            case 156 -> "d_spikewheel_03_001.png";
            case 157 -> "d_wave_01_001.png";
            case 158 -> "d_wave_02_001.png";
            case 159 -> "d_wave_03_001.png";
            case 160 -> "blockOutline_01_001.png";
            case 161 -> "lightsquare_01_02_001.png";
            case 162 -> "blockOutline_03_001.png";
            case 163 -> "blockOutline_06_001.png";
            case 164 -> "square_g_05_001.png";
            case 165 -> "blockOutline_04_001.png";
            case 166 -> "blockOutline_05_001.png";
            case 167 -> "blockOutline_06_001.png";
            case 168 -> "blockOutline_01_001.png";
            case 169 -> "blockOutline_01_001.png";
            case 170 -> "square_h_01_001.png";
            case 171 -> "square_h_02_001.png";
            case 172 -> "square_h_03_001.png";
            case 173 -> "square_h_04_001.png";
            case 174 -> "square_h_05_001.png";
            case 175 -> "square_h_06_001.png";
            case 176 -> "square_h_07_001.png";
            case 177 -> "iceSpike_01_001.png";
            case 178 -> "iceSpike_02_001.png";
            case 179 -> "iceSpike_03_001.png";
            case 180 -> "d_cartwheel_01_001.png";
            case 181 -> "d_cartwheel_02_001.png";
            case 182 -> "d_cartwheel_03_001.png";
            case 183 -> "blade_b_01_001.png";
            case 184 -> "blade_b_02_001.png";
            case 185 -> "blade_b_03_001.png";
            case 186 -> "blade_01_001.png";
            case 187 -> "blade_02_001.png";
            case 188 -> "blade_03_001.png";
            case 190 -> "d_art_02_001.png";
            case 191 -> "fakeSpike_01_001.png";
            case 192 -> "square_h_08_001.png";
            case 193 -> "square_g_11_001.png";
            case 194 -> "square_h_09_001.png";
            case 195 -> "square_01_small_001.png";
            case 196 -> "plank_01_small_001.png";
            case 197 -> "square_h_10_001.png";
            case 198 -> "fakeSpike_02_001.png";
            case 199 -> "fakeSpike_03_001.png";
            case 200 -> "boost_01_001.png";
            case 201 -> "boost_02_001.png";
            case 202 -> "boost_03_001.png";
            case 203 -> "boost_04_001.png";
            case 204 -> "invis_plank_01_small_001.png";
            case 205 -> "invis_spike_02_001.png";
            case 206 -> "invis_square_01_small_001.png";
            case 207 -> "lightsquare_01_01_001.png";
            case 208 -> "lightsquare_01_02_001.png";
            case 209 -> "lightsquare_01_03_001.png";
            case 210 -> "lightsquare_01_04_001.png";
            case 211 -> "lightsquare_01_05_color_001.png";
            case 212 -> "lightsquare_01_06_001.png";
            case 213 -> "lightsquare_01_07_001.png";
            case 215 -> "colorPlank_01_001.png";
            case 216 -> "colorSpike_01_001.png";
            case 217 -> "colorSpike_02_001.png";
            case 218 -> "colorSpike_03_001.png";
            case 219 -> "colorPlank_01_small_001.png";
            case 220 -> "colorSquare_01_small_001.png";
            case 222 -> "d_roundCloud_01_001.png";
            case 223 -> "d_roundCloud_02_001.png";
            case 224 -> "d_roundCloud_03_001.png";
            case 225 -> "d_swirve_01_001.png";
            case 226 -> "d_swirve_02_001.png";
            case 227 -> "d_bar_01_001.png";
            case 228 -> "d_bar_02_001.png";
            case 229 -> "d_bar_03_001.png";
            case 230 -> "d_bar_04_001.png";
            case 231 -> "d_smallbar_01_001.png";
            case 232 -> "d_smallbar_02_001.png";
            case 233 -> "d_square_03_01_001.png";
            case 234 -> "d_square_03_02_001.png";
            case 235 -> "d_square_03_03_001.png";
            case 236 -> "d_circle_01_001.png";
            case 237 -> "d_link_01_001.png";
            case 238 -> "d_link_02_001.png";
            case 239 -> "d_link_03_001.png";
            case 240 -> "d_link_04_001.png";
            case 241 -> "d_link_05_001.png";
            case 242 -> "d_bar_07_001.png";
            case 243 -> "pit_04_02_001.png";
            case 244 -> "pit_04_03_001.png";
            case 245 -> "square_f_brick01_001.png";
            case 246 -> "square_f_brick02_001.png";
            case 247 -> "lightsquare_02_01_001.png";
            case 248 -> "lightsquare_02_02_001.png";
            case 249 -> "lightsquare_02_03_001.png";
            case 250 -> "lightsquare_02_04_001.png";
            case 251 -> "lightsquare_02_05_color_001.png";
            case 252 -> "lightsquare_02_06_001.png";
            case 253 -> "lightsquare_02_07_001.png";
            case 254 -> "lightsquare_02_08_001.png";
            case 255 -> "lightsquare_03_01_001.png";
            case 256 -> "lightsquare_03_02_001.png";
            case 257 -> "lightsquare_03_03_001.png";
            case 258 -> "lightsquare_03_04_001.png";
            case 259 -> "lightsquare_03_05_color_001.png";
            case 260 -> "lightsquare_03_06_001.png";
            case 261 -> "lightsquare_03_07_001.png";
            case 263 -> "lightsquare_04_02_001.png";
            case 264 -> "lightsquare_04_02_001.png";
            case 265 -> "lightsquare_04_02_001.png";
            case 266 -> "lightsquare_04_05_color_001.png";
            case 267 -> "lightsquare_04_02_001.png";
            case 268 -> "lightsquare_04_sideLine_001.png";
            case 269 -> "lightsquare_05_01_001.png";
            case 270 -> "lightsquare_05_02_001.png";
            case 271 -> "lightsquare_05_03_001.png";
            case 272 -> "lightsquare_05_04_001.png";
            case 273 -> "lightsquare_05_05_color_001.png";
            case 274 -> "lightsquare_05_06_001.png";
            case 275 -> "lightsquare_05_07_001.png";
            case 277 -> "lightsquare_05_brick02_001.png";
            case 278 -> "lightsquare_05_brick03_001.png";
            case 279 -> "d_square_01_001.png";
            case 280 -> "d_square_02_001.png";
            case 281 -> "d_square_04_001.png";
            case 282 -> "d_square_05_001.png";
            case 283 -> "d_smallbar_03_001.png";
            case 284 -> "d_smallbar_04_001.png";
            case 285 -> "d_smallbar_05_001.png";
            case 286 -> "portal_11_front_001.png";
            case 287 -> "portal_12_front_001.png";
            case 289 -> "blockOutline_14_001.png";
            case 291 -> "blockOutline_15_001.png";
            case 294 -> "blockOutline_14_001.png";
            case 295 -> "blockOutline_15_001.png";
            case 296 -> "triangle_b_square_01_001.png";
            case 297 -> "triangle_b_square_02_001.png";
            case 299 -> "blockOutline_14_001.png";
            case 301 -> "blockOutline_15_001.png";
            case 305 -> "blockOutline_16_001.png";
            case 307 -> "blockOutline_17_001.png";
            case 309 -> "blockOutline_14_001.png";
            case 311 -> "blockOutline_15_001.png";
            case 315 -> "blockOutline_14_001.png";
            case 317 -> "blockOutline_15_001.png";
            case 321 -> "blockOutline_14_001.png";
            case 323 -> "blockOutline_15_001.png";
            case 324 -> "triangle_g_square_01_001.png";
            case 325 -> "triangle_g_square_02_001.png";
            case 326 -> "triangle_h_01_001.png";
            case 327 -> "triangle_h_02_001.png";
            case 328 -> "triangle_h_square_01_001.png";
            case 329 -> "triangle_h_square_02_001.png";
            case 331 -> "blockOutline_14_001.png";
            case 333 -> "blockOutline_15_001.png";
            case 337 -> "blockOutline_14_001.png";
            case 339 -> "blockOutline_15_001.png";
            case 343 -> "blockOutline_14_001.png";
            case 345 -> "blockOutline_15_001.png";
            case 349 -> "blockOutline_16_001.png";
            case 351 -> "blockOutline_17_001.png";
            case 353 -> "blockOutline_14_001.png";
            case 355 -> "blockOutline_15_001.png";
            case 358 -> "triangle_g_square_03_001.png";
            case 363 -> "pit_01_slope_01_001.png";
            case 364 -> "pit_01_slope_02_001.png";
            case 365 -> "pit_01_low_001.png";
            case 366 -> "pit_04_slope_01_001.png";
            case 367 -> "pit_04_slope_02_001.png";
            case 368 -> "pit_04_low_001.png";
            case 369 -> "plank_01_02_001.png";
            case 370 -> "plank_01_03_001.png";
            case 371 -> "blockOutline_14_001.png";
            case 372 -> "blockOutline_15_001.png";
            case 373 -> "plank_01_square_01_001.png";
            case 374 -> "plank_01_square_02_001.png";
            case 375 -> "d_rotatingBall_01_001.png";
            case 376 -> "d_rotatingBall_02_001.png";
            case 377 -> "d_rotatingBall_03_001.png";
            case 378 -> "d_rotatingBall_04_001.png";
            case 392 -> "spike_04_001.png";
            case 393 -> "fakeSpike_04_001.png";
            case 394 -> "d_geometric_01_001.png";
            case 395 -> "d_geometric_02_001.png";
            case 396 -> "d_geometric_03_001.png";
            case 397 -> "darkblade_01_001.png";
            case 398 -> "darkblade_02_001.png";
            case 399 -> "darkblade_03_001.png";
            case 405 -> "d_ball_09_001.png";
            case 406 -> "d_grass_01_001.png";
            case 407 -> "d_grass_02_001.png";
            case 408 -> "d_grass_03_001.png";
            case 409 -> "d_link_b_01_001.png";
            case 410 -> "d_link_b_02_001.png";
            case 411 -> "d_link_b_03_001.png";
            case 412 -> "d_link_b_04_001.png";
            case 413 -> "d_link_b_05_001.png";
            case 414 -> "d_grass_04_001.png";
            case 419 -> "d_spikeWave_01_001.png";
            case 420 -> "d_spikeWave_02_001.png";
            case 421 -> "pit_05_001.png";
            case 422 -> "pit_05_02_001.png";
            case 446 -> "pit_06_001.png";
            case 447 -> "pit_06_2_001.png";
            case 448 -> "d_pit06wave_01_001.png";
            case 449 -> "d_pit06wave_02_001.png";
            case 450 -> "d_pillar_01_001.png";
            case 451 -> "d_pillar_02_001.png";
            case 452 -> "d_pillar_03_001.png";
            case 453 -> "d_link_c_01_001.png";
            case 454 -> "d_link_c_02_001.png";
            case 455 -> "d_link_c_03_001.png";
            case 456 -> "d_link_c_04_001.png";
            case 457 -> "d_link_c_05_001.png";
            case 458 -> "colorSpike_04_001.png";
            case 459 -> "invis_spike_04_001.png";
            case 460 -> "d_arrow_02_001.png";
            case 461 -> "d_thorn_01_001.png";
            case 462 -> "d_thorn_02_001.png";
            case 463 -> "d_thorn_03_001.png";
            case 464 -> "d_thorn_04_001.png";
            case 465 -> "d_thorn_05_001.png";
            case 466 -> "d_thorn_06_001.png";
            case 467 -> "blockOutline_01_001.png";
            case 468 -> "blockOutline_02_001.png";
            case 469 -> "blockOutline_03_001.png";
            case 470 -> "blockOutline_04_001.png";
            case 471 -> "blockOutline_05_001.png";
            case 472 -> "blockOutline_06_001.png";
            case 473 -> "blockOutline_07_001.png";
            case 474 -> "blockOutline_08_001.png";
            case 475 -> "blockOutline_09_001.png";
            case 476 -> "block001_01_001.png";
            case 477 -> "block001_02_001.png";
            case 478 -> "block001_03_001.png";
            case 479 -> "block001_04_001.png";
            case 480 -> "block001_05_001.png";
            case 481 -> "block001_06_001.png";
            case 482 -> "block001_07_001.png";
            case 483 -> "blockOutline_14_001.png";
            case 484 -> "blockOutline_15_001.png";
            case 485 -> "block002_01_001.png";
            case 486 -> "block002_02_001.png";
            case 487 -> "block002_03_001.png";
            case 488 -> "block002_04_001.png";
            case 489 -> "block002_05_001.png";
            case 490 -> "block002_06_001.png";
            case 491 -> "block002_07_001.png";
            case 492 -> "blockOutline_14_001.png";
            case 493 -> "blockOutline_15_001.png";
            case 494 -> "d_arrow_03_001.png";
            case 495 -> "d_largeSquare_01_001.png";
            case 496 -> "d_largeSquare_02_001.png";
            case 497 -> "d_circle_02_001.png";
            case 498 -> "d_03_chain_01_001.png";
            case 499 -> "d_03_chain_02_001.png";
            case 500 -> "d_swirve_03_001.png";
            case 501 -> "d_swirve_04_001.png";
            case 502 -> "square_09_001.png";
            case 503 -> "d_gradient_01_001.png";
            case 504 -> "d_gradient_02_001.png";
            case 505 -> "d_gradient_03_001.png";
            case 506 -> "persp_outline_01_001.png";
            case 507 -> "persp_outline_02_001.png";
            case 508 -> "persp_outline_03_001.png";
            case 509 -> "persp_outline_04_001.png";
            case 510 -> "persp_outline_05_001.png";
            case 511 -> "persp_outline_06_001.png";
            case 512 -> "persp_outline_07_001.png";
            case 513 -> "persp_outline_08_001.png";
            case 514 -> "persp_outline_09_001.png";
            case 515 -> "persp_outline_01_001.png";
            case 516 -> "persp_outline_02_001.png";
            case 517 -> "persp_outline_03_001.png";
            case 518 -> "persp_outline_04_001.png";
            case 519 -> "persp_outline_05_001.png";
            case 520 -> "persp_outline_06_001.png";
            case 521 -> "persp_outline_07_001.png";
            case 522 -> "persp_outline_08_001.png";
            case 523 -> "persp_outline_09_001.png";
            case 524 -> "persp_outline_01_001.png";
            case 525 -> "persp_outline_02_001.png";
            case 526 -> "persp_outline_03_001.png";
            case 527 -> "persp_outline_04_001.png";
            case 528 -> "persp_outline_05_001.png";
            case 529 -> "persp_outline_06_001.png";
            case 530 -> "persp_outline_07_001.png";
            case 531 -> "persp_outline_08_001.png";
            case 532 -> "persp_outline_09_001.png";
            case 533 -> "persp_outline_01_001.png";
            case 534 -> "persp_outline_02_001.png";
            case 535 -> "persp_outline_03_001.png";
            case 536 -> "persp_outline_04_001.png";
            case 537 -> "persp_outline_05_001.png";
            case 538 -> "persp_outline_06_001.png";
            case 539 -> "persp_outline_07_001.png";
            case 540 -> "persp_outline_08_001.png";
            case 541 -> "persp_outline_09_001.png";
            case 542 -> "persp_outline_01_001.png";
            case 543 -> "persp_outline_02_001.png";
            case 544 -> "persp_outline_03_001.png";
            case 545 -> "persp_outline_04_001.png";
            case 546 -> "persp_outline_05_001.png";
            case 547 -> "persp_outline_06_001.png";
            case 548 -> "persp_outline_07_001.png";
            case 549 -> "persp_outline_08_001.png";
            case 550 -> "persp_outline_09_001.png";
            case 551 -> "persp_outline_01_001.png";
            case 552 -> "persp_outline_02_001.png";
            case 553 -> "persp_outline_03_001.png";
            case 554 -> "persp_outline_04_001.png";
            case 555 -> "persp_outline_05_001.png";
            case 556 -> "persp_outline_06_001.png";
            case 557 -> "persp_outline_07_001.png";
            case 558 -> "persp_outline_08_001.png";
            case 559 -> "persp_outline_09_001.png";
            case 560 -> "persp_outline_01_001.png";
            case 561 -> "persp_outline_02_001.png";
            case 562 -> "persp_outline_03_001.png";
            case 563 -> "persp_outline_04_001.png";
            case 564 -> "persp_outline_05_001.png";
            case 565 -> "persp_outline_06_001.png";
            case 566 -> "persp_outline_07_001.png";
            case 567 -> "persp_outline_08_001.png";
            case 568 -> "persp_outline_09_001.png";
            case 569 -> "persp_outline_01_001.png";
            case 570 -> "persp_outline_02_001.png";
            case 571 -> "persp_outline_03_001.png";
            case 572 -> "persp_outline_04_001.png";
            case 573 -> "persp_outline_05_001.png";
            case 574 -> "persp_outline_06_001.png";
            case 575 -> "persp_outline_07_001.png";
            case 576 -> "persp_outline_08_001.png";
            case 577 -> "persp_outline_09_001.png";
            case 578 -> "persp_outline_01_001.png";
            case 579 -> "persp_outline_02_001.png";
            case 580 -> "persp_outline_03_001.png";
            case 581 -> "persp_outline_04_001.png";
            case 582 -> "persp_outline_05_001.png";
            case 583 -> "persp_outline_06_001.png";
            case 584 -> "persp_outline_07_001.png";
            case 585 -> "persp_outline_08_001.png";
            case 586 -> "persp_outline_09_001.png";
            case 587 -> "persp_outline_01_001.png";
            case 588 -> "persp_outline_02_001.png";
            case 589 -> "persp_outline_03_001.png";
            case 590 -> "persp_outline_04_001.png";
            case 591 -> "persp_outline_05_001.png";
            case 592 -> "persp_outline_06_001.png";
            case 593 -> "persp_outline_07_001.png";
            case 594 -> "persp_outline_08_001.png";
            case 595 -> "persp_outline_09_001.png";
            case 596 -> "persp_outline_01_001.png";
            case 597 -> "persp_outline_02_001.png";
            case 598 -> "persp_outline_03_001.png";
            case 599 -> "persp_outline_04_001.png";
            case 600 -> "persp_outline_05_001.png";
            case 601 -> "persp_outline_06_001.png";
            case 602 -> "persp_outline_07_001.png";
            case 603 -> "persp_outline_08_001.png";
            case 604 -> "persp_outline_09_001.png";
            case 605 -> "persp_outline_01_001.png";
            case 606 -> "persp_outline_02_001.png";
            case 607 -> "persp_outline_03_001.png";
            case 608 -> "persp_outline_04_001.png";
            case 609 -> "persp_outline_05_001.png";
            case 610 -> "persp_outline_06_001.png";
            case 611 -> "persp_outline_07_001.png";
            case 612 -> "persp_outline_08_001.png";
            case 613 -> "persp_outline_09_001.png";
            case 614 -> "persp_outline_01_001.png";
            case 615 -> "persp_outline_02_001.png";
            case 616 -> "persp_outline_03_001.png";
            case 617 -> "persp_outline_04_001.png";
            case 618 -> "persp_outline_05_001.png";
            case 619 -> "persp_outline_06_001.png";
            case 620 -> "persp_outline_07_001.png";
            case 621 -> "persp_outline_08_001.png";
            case 622 -> "persp_outline_09_001.png";
            case 623 -> "persp_outline_01_001.png";
            case 624 -> "persp_outline_02_001.png";
            case 625 -> "persp_outline_03_001.png";
            case 626 -> "persp_outline_04_001.png";
            case 627 -> "persp_outline_05_001.png";
            case 628 -> "persp_outline_06_001.png";
            case 629 -> "persp_outline_07_001.png";
            case 630 -> "persp_outline_08_001.png";
            case 631 -> "persp_outline_09_001.png";
            case 632 -> "persp_outline_01_001.png";
            case 633 -> "persp_outline_02_001.png";
            case 634 -> "persp_outline_03_001.png";
            case 635 -> "persp_outline_04_001.png";
            case 636 -> "persp_outline_05_001.png";
            case 637 -> "persp_outline_06_001.png";
            case 638 -> "persp_outline_07_001.png";
            case 639 -> "persp_outline_08_001.png";
            case 640 -> "persp_outline_09_001.png";
            case 641 -> "block003_part03_001.png";
            case 642 -> "block003_part04_001.png";
            case 643 -> "block003_part06_001.png";
            case 644 -> "block003_part05_001.png";
            case 645 -> "block003_part01_001.png";
            case 646 -> "block003_part01_001.png";
            case 647 -> "block003_part02_001.png";
            case 648 -> "block003_part01_001.png";
            case 649 -> "block003_part01_001.png";
            case 650 -> "block003_part05_001.png";
            case 651 -> "blockOutline_14_001.png";
            case 652 -> "blockOutline_15_001.png";
            case 653 -> "d_block04_piece01_001.png";
            case 654 -> "d_block04_piece02_001.png";
            case 655 -> "d_block04_piece03_001.png";
            case 656 -> "d_block04_piece04_001.png";
            case 657 -> "d_block04_piece05_001.png";
            case 658 -> "d_block04_piece06_001.png";
            case 659 -> "d_block04_piece07_001.png";
            case 660 -> "portal_13_front_001.png";
            case 661 -> "blockOutline_10_001.png";
            case 662 -> "blockOutline_11_001.png";
            case 663 -> "blockOutline_12_001.png";
            case 664 -> "blockOutline_13_001.png";
            case 665 -> "blockOutline_14_001.png";
            case 666 -> "blockOutline_15_001.png";
            case 667 -> "pit_07_001.png";
            case 668 -> "d_pixelArt01_001_001.png";
            case 669 -> "d_pixelArt01_002_001.png";
            case 670 -> "d_pixelArt01_003_001.png";
            case 671 -> "d_pixelArt01_004_001.png";
            case 672 -> "d_pixelArt01_005_001.png";
            case 673 -> "invis_triangle_02_001.png";
            case 674 -> "invis_triangle_04_001.png";
            case 675 -> "blackCogwheel_01_001.png";
            case 676 -> "blackCogwheel_02_001.png";
            case 677 -> "blackCogwheel_03_001.png";
            case 678 -> "lightBlade_01_001.png";
            case 679 -> "lightBlade_02_001.png";
            case 680 -> "lightBlade_03_001.png";
            case 681 -> "triangle_a_02_001.png";
            case 682 -> "triangle_a_04_001.png";
            case 683 -> "triangle_c_02_001.png";
            case 684 -> "triangle_c_04_001.png";
            case 685 -> "triangle_d_02_001.png";
            case 686 -> "triangle_d_04_001.png";
            case 687 -> "lighttriangle_01_02_color_001.png";
            case 688 -> "lighttriangle_01_04_color_001.png";
            case 689 -> "triangle_f_02_001.png";
            case 690 -> "triangle_f_04_001.png";
            case 691 -> "triangle_g_02_001.png";
            case 692 -> "triangle_g_04_001.png";
            case 693 -> "lighttriangle_01_02_color_001.png";
            case 694 -> "lighttriangle_01_04_color_001.png";
            case 695 -> "lighttriangle_02_02_color_001.png";
            case 696 -> "lighttriangle_02_04_color_001.png";
            case 697 -> "lighttriangle_03_02_color_001.png";
            case 698 -> "lighttriangle_03_04_color_001.png";
            case 699 -> "lighttriangle_04_02_color_001.png";
            case 700 -> "lighttriangle_04_04_color_001.png";
            case 701 -> "lighttriangle_05_02_color_001.png";
            case 702 -> "lighttriangle_05_04_color_001.png";
            case 703 -> "block001_slope_01_001.png";
            case 704 -> "block001_slope_02_001.png";
            case 705 -> "block002_slope_01_001.png";
            case 706 -> "block002_slope_02_001.png";
            case 707 -> "block003_slope_01_001.png";
            case 708 -> "block003_slope_02_001.png";
            case 709 -> "blockOutline_14_001.png";
            case 710 -> "blockOutline_15_001.png";
            case 711 -> "blockOutline_14_001.png";
            case 712 -> "blockOutline_15_001.png";
            case 713 -> "block004_slope_01_001.png";
            case 714 -> "block004_slope_02_001.png";
            case 715 -> "block004_slope_01b_001.png";
            case 716 -> "block004_slope_02b_001.png";
            case 719 -> "pit_07_shine_001.png";
            case 720 -> "pit_07_2_001.png";
            case 721 -> "pit_07_2_shine_001.png";
            case 722 -> "d_block04_piece08_001.png";
            case 723 -> "d_block04_piece09_001.png";
            case 724 -> "d_block04_piece10_001.png";
            case 725 -> "d_link_b_01_color_001.png";
            case 726 -> "blockOutline_14_001.png";
            case 727 -> "blockOutline_15_001.png";
            case 728 -> "blockOutline_14_001.png";
            case 729 -> "blockOutline_15_001.png";
            case 730 -> "block004_slope_01c_001.png";
            case 731 -> "block004_slope_02c_001.png";
            case 732 -> "block004_slope_01d_001.png";
            case 733 -> "block004_slope_02d_001.png";
            case 734 -> "d_block04_piece11_001.png";
            case 735 -> "d_block04_piece12_001.png";
            case 736 -> "d_block04_piece13_001.png";
            case 737 -> "square_g_12_001.png";
            case 738 -> "d_pixelArt01_006_001.png";
            case 739 -> "block003_part04_001.png";
            case 740 -> "blade_01_001.png";
            case 741 -> "blade_02_001.png";
            case 742 -> "blade_03_001.png";
            case 744 -> "edit_eTint3DLBtn_001.png";
            case 745 -> "portal_14_front_001.png";
            case 747 -> "portal_15_front_001.png";
            case 749 -> "portal_16_front_001.png";
            case 752 -> "block005_02_001.png";
            case 753 -> "block005_03_001.png";
            case 754 -> "block005_04_001.png";
            case 755 -> "block005_05_001.png";
            case 756 -> "block005_06_001.png";
            case 757 -> "block005_07_001.png";
            case 758 -> "block005_08_001.png";
            case 759 -> "block005_09_001.png";
            case 762 -> "block005_slope_01_001.png";
            case 763 -> "block005_slope_02_001.png";
            case 764 -> "block005_slope_square_01_001.png";
            case 765 -> "block005_slope_square_02_001.png";
            case 766 -> "block005_slope_square_03_001.png";
            case 767 -> "d_spikeWave_03_001.png";
            case 768 -> "pit_05_03_001.png";
            case 769 -> "plank005_01_001.png";
            case 770 -> "plank005_02_001.png";
            case 771 -> "plank005_slope_01_001.png";
            case 772 -> "plank005_slope_02_001.png";
            case 773 -> "plank005_slope_square_01_001.png";
            case 774 -> "plank005_slope_square_02_001.png";
            case 775 -> "plank005_slope_square_03_001.png";
            case 807 -> "block007_01_001.png";
            case 808 -> "block007_01_001.png";
            case 809 -> "block007_02_001.png";
            case 810 -> "block007_02_001.png";
            case 811 -> "block007_03_001.png";
            case 812 -> "block007_03_001.png";
            case 813 -> "block007_04_001.png";
            case 814 -> "block007_04_001.png";
            case 815 -> "block007_05_001.png";
            case 816 -> "block007_05_001.png";
            case 817 -> "block007_06_001.png";
            case 818 -> "block007_06_001.png";
            case 819 -> "block007_07_001.png";
            case 820 -> "block007_07_001.png";
            case 821 -> "block007_08_001.png";
            case 822 -> "block007_08_001.png";
            case 823 -> "block007_09_001.png";
            case 824 -> "block007_09_001.png";
            case 825 -> "block007_09_001.png";
            case 826 -> "block007_slope_01_001.png";
            case 827 -> "block007_slope_01_001.png";
            case 828 -> "block007_slope_02_001.png";
            case 829 -> "block007_slope_02_001.png";
            case 830 -> "block007_slope_square_01_001.png";
            case 831 -> "block007_slope_square_01_001.png";
            case 832 -> "block007_slope_square_02_001.png";
            case 833 -> "block007_slope_square_02_001.png";
            case 841 -> "block007b_01_001.png";
            case 842 -> "block007b_05_001.png";
            case 843 -> "block007b_03_001.png";
            case 844 -> "block007b_03_001.png";
            case 845 -> "block007b_05_001.png";
            case 846 -> "block007b_06_001.png";
            case 847 -> "block007b_07_001.png";
            case 848 -> "block007b_08_001.png";
            case 850 -> "block008_02_001.png";
            case 853 -> "block008_03_001.png";
            case 854 -> "block008_04_001.png";
            case 855 -> "block008_05_001.png";
            case 856 -> "block008_06_001.png";
            case 857 -> "block008_07_001.png";
            case 859 -> "block008_08_001.png";
            case 861 -> "block008_09_001.png";
            case 862 -> "block008_10_001.png";
            case 863 -> "block008_11_001.png";
            case 867 -> "block009_01_001.png";
            case 868 -> "block009_02_001.png";
            case 869 -> "block009_03_001.png";
            case 870 -> "block009_04_001.png";
            case 871 -> "block009_05_001.png";
            case 872 -> "block009_06_001.png";
            case 873 -> "block009_part_01_001.png";
            case 874 -> "block009_part_02_001.png";
            case 877 -> "block009_slope_01_001.png";
            case 878 -> "block009_slope_02_001.png";
            case 880 -> "block009b_01_001.png";
            case 881 -> "block009b_02_001.png";
            case 882 -> "block009b_03_001.png";
            case 883 -> "block009b_04_001.png";
            case 884 -> "block009b_05_001.png";
            case 885 -> "block009b_06_001.png";
            case 886 -> "blockOutline_14_001.png";
            case 887 -> "blockOutline_15_001.png";
            case 888 -> "block009b_slope_01_001.png";
            case 889 -> "block009b_slope_02_001.png";
            case 890 -> "block009c_01_001.png";
            case 891 -> "block009c_02_001.png";
            case 893 -> "block009c_04_001.png";
            case 894 -> "block009c_05_001.png";
            case 895 -> "block009c_slope_01_001.png";
            case 896 -> "block009c_slope_02_001.png";
            case 899 -> "edit_eTintCol01Btn_001.png";
            case 900 -> "edit_eTintG2Btn_001.png";
            case 901 -> "edit_eMoveComBtn_001.png";
            case 902 -> "persp_outline_08_001.png";
            case 903 -> "block005_10_001.png";
            case 904 -> "block005_11_001.png";
            case 905 -> "block005_12_001.png";
            case 906 -> "d_grass_05_001.png";
            case 907 -> "d_grassArt_01_001.png";
            case 908 -> "d_grassArt_02_001.png";
            case 909 -> "d_grassArt_03_001.png";
            case 910 -> "d_grassArt_04_001.png";
            case 911 -> "block005_13_001.png";
            case 914 -> "edit_eStartPosBtn_001.png";
            case 915 -> "edit_eTintLBtn_001.png";
            case 916 -> "d_whiteBlock_01_001.png";
            case 917 -> "d_whiteBlock_02_001.png";
            case 918 -> "GJBeast01_01_001.png";
            case 919 -> "dA_blackSludge_01_001.png";
            case 920 -> "Fire_03_looped_001.png";
            case 921 -> "Fire_04_looped_004.png";
            case 923 -> "Fire_01_looped_001.png";
            case 924 -> "Fire_02_looped_001.png";
            case 925 -> "d_rainbow_01_001.png";
            case 926 -> "d_rainbow_02_001.png";
            case 927 -> "block010_01_001.png";
            case 928 -> "block010_02_001.png";
            case 929 -> "block010_03_001.png";
            case 930 -> "block010_04_001.png";
            case 931 -> "block010_piece_01_001.png";
            case 932 -> "block010_06_001.png";
            case 933 -> "block010_07_001.png";
            case 934 -> "block010_08_001.png";
            case 935 -> "block010_piece_01_001.png";
            case 936 -> "d_artCloud_01_001.png";
            case 937 -> "d_artCloud_02_001.png";
            case 938 -> "d_artCloud_03_001.png";
            case 939 -> "d_flower01_01_001.png";
            case 940 -> "d_grassDetail_01_001.png";
            case 941 -> "d_grassDetail_02_001.png";
            case 942 -> "d_grassDetail_03_001.png";
            case 943 -> "persp_outline_01_001.png";
            case 944 -> "persp_outline_02_001.png";
            case 945 -> "persp_outline_03_001.png";
            case 946 -> "persp_outline_04_001.png";
            case 947 -> "persp_outline_05_001.png";
            case 948 -> "persp_outline_06_001.png";
            case 949 -> "persp_outline_07_001.png";
            case 950 -> "persp_outline_08_001.png";
            case 951 -> "persp_outline_09_001.png";
            case 952 -> "block005b_02_001.png";
            case 953 -> "block005b_03_001.png";
            case 954 -> "block005b_04_001.png";
            case 955 -> "block005b_05_001.png";
            case 956 -> "block005b_06_001.png";
            case 957 -> "block005b_07_001.png";
            case 958 -> "block005b_08_001.png";
            case 959 -> "block005b_09_001.png";
            case 960 -> "block005b_slope_01_001.png";
            case 961 -> "block005b_slope_02_001.png";
            case 964 -> "block005b_slope_square_01_001.png";
            case 965 -> "block005b_slope_square_02_001.png";
            case 966 -> "block005b_slope_square_03_001.png";
            case 967 -> "plank005b_01_001.png";
            case 968 -> "plank005b_02_001.png";
            case 969 -> "plank005b_slope_01_001.png";
            case 970 -> "plank005b_slope_02_001.png";
            case 971 -> "plank005b_slope_square_01_001.png";
            case 972 -> "plank005b_slope_square_02_001.png";
            case 973 -> "plank005b_slope_square_03_001.png";
            case 974 -> "block005b_10_001.png";
            case 975 -> "block005b_11_001.png";
            case 976 -> "block005b_12_001.png";
            case 977 -> "block005b_13_001.png";
            case 980 -> "persp_outline_01_001.png";
            case 981 -> "persp_outline_02_001.png";
            case 982 -> "persp_outline_03_001.png";
            case 983 -> "persp_outline_04_001.png";
            case 984 -> "persp_outline_05_001.png";
            case 985 -> "persp_outline_06_001.png";
            case 986 -> "persp_outline_07_001.png";
            case 987 -> "persp_outline_08_001.png";
            case 988 -> "persp_outline_09_001.png";
            case 989 -> "pit_07_3_001.png";
            case 990 -> "pit_07_3_shine_001.png";
            case 991 -> "pit_07_4_001.png";
            case 992 -> "pit_07_4_shine_001.png";
            case 997 -> "d_ringSeg_01_001.png";
            case 998 -> "d_ringSeg_02_001.png";
            case 999 -> "d_ringSeg_03_001.png";
            case 1000 -> "d_ringSeg_04_001.png";
            case 1001 -> "d_link_d_01_001.png";
            case 1002 -> "d_link_d_02_001.png";
            case 1003 -> "d_link_d_03_001.png";
            case 1004 -> "d_link_d_04_001.png";
            case 1005 -> "d_link_d_05_001.png";
            case 1006 -> "edit_ePulseBtn_001.png";
            case 1007 -> "edit_eAlphaBtn_001.png";
            case 1009 -> "d_gradient_b_02_001.png";
            case 1010 -> "d_gradient_b_03_001.png";
            case 1011 -> "d_gradient_c_01_001.png";
            case 1012 -> "d_gradient_c_02_001.png";
            case 1013 -> "d_gradient_c_03_001.png";
            case 1014 -> "block010_slope_01_001.png";
            case 1015 -> "block010_slope_02_001.png";
            case 1016 -> "block010_slope_square_01_001.png";
            case 1017 -> "block010_slope_square_02_001.png";
            case 1018 -> "block010_slope_square_03_001.png";
            case 1019 -> "d_flashRing_01_001.png";
            case 1020 -> "d_flashRing_02_001.png";
            case 1021 -> "d_flashRing_03_001.png";
            case 1022 -> "gravJumpRing_01_001.png";
            case 1024 -> "persp_outline_01_001.png";
            case 1025 -> "persp_outline_02_001.png";
            case 1026 -> "persp_outline_03_001.png";
            case 1027 -> "persp_outline_04_001.png";
            case 1028 -> "persp_outline_05_001.png";
            case 1029 -> "persp_outline_06_001.png";
            case 1030 -> "persp_outline_07_001.png";
            case 1031 -> "persp_outline_08_001.png";
            case 1032 -> "persp_outline_09_001.png";
            case 1033 -> "block005_slope_03_001.png";
            case 1034 -> "block005_slope_04_001.png";
            case 1035 -> "block005_slope_square_04_001.png";
            case 1036 -> "block005_slope_square_05_001.png";
            case 1037 -> "block005b_slope_03_001.png";
            case 1038 -> "block005b_slope_04_001.png";
            case 1039 -> "block005b_slope_square_04_001.png";
            case 1040 -> "block005b_slope_square_05_001.png";
            case 1041 -> "block005_slope_05_001.png";
            case 1042 -> "block005_slope_06_001.png";
            case 1043 -> "block005b_slope_05_001.png";
            case 1044 -> "block005b_slope_06_001.png";
            case 1045 -> "block005_16_001.png";
            case 1046 -> "block005_17_001.png";
            case 1047 -> "block005b_16_001.png";
            case 1048 -> "block005b_17_001.png";
            case 1049 -> "edit_eToggleBtn_001.png";
            case 1050 -> "d_animWave_01_base_001.png";
            case 1051 -> "d_animWave_02_base_001.png";
            case 1052 -> "d_animWave_03_base_001.png";
            case 1053 -> "d_animLoading_01_001.png";
            case 1054 -> "d_animLoading_02_001.png";
            case 1055 -> "d_pickupCircle_01_001.png";
            case 1056 -> "d_pickupCircle_01_001.png";
            case 1057 -> "d_pickupCircle_01_001.png";
            case 1058 -> "d_spiral_01_001.png";
            case 1059 -> "d_spiral_02_001.png";
            case 1060 -> "d_spiral_03_001.png";
            case 1061 -> "d_spiral_04_001.png";
            case 1062 -> "block009b_07_001.png";
            case 1063 -> "persp_outline_01_001.png";
            case 1064 -> "persp_outline_02_001.png";
            case 1065 -> "persp_outline_03_001.png";
            case 1066 -> "persp_outline_04_001.png";
            case 1067 -> "persp_outline_05_001.png";
            case 1068 -> "persp_outline_06_001.png";
            case 1069 -> "persp_outline_07_001.png";
            case 1070 -> "persp_outline_08_001.png";
            case 1071 -> "persp_outline_09_001.png";
            case 1075 -> "block003_part03_001.png";
            case 1076 -> "block003_part01_001.png";
            case 1077 -> "block003_part01_001.png";
            case 1078 -> "block007_01_small_001.png";
            case 1079 -> "plank007_01_001.png";
            case 1080 -> "plank007_02_001.png";
            case 1081 -> "plank007_03_001.png";
            case 1082 -> "block007_01_001.png";
            case 1083 -> "block007_02_001.png";
            case 1084 -> "block007_03_001.png";
            case 1085 -> "block007_04_001.png";
            case 1086 -> "block007_05_001.png";
            case 1087 -> "block007_06_001.png";
            case 1088 -> "block007_07_001.png";
            case 1089 -> "block007_08_001.png";
            case 1090 -> "block007_09_001.png";
            case 1091 -> "block007_slope_01_001.png";
            case 1092 -> "block007_slope_02_001.png";
            case 1093 -> "block007_slope_square_01_001.png";
            case 1094 -> "block007_slope_square_02_001.png";
            case 1095 -> "block007_01_small_001.png";
            case 1096 -> "plank007_01_001.png";
            case 1097 -> "plank007_02_001.png";
            case 1098 -> "plank007_03_001.png";
            case 1099 -> "block007_bgcolor_001_001.png";
            case 1100 -> "block007_bgcolor_002_001.png";
            case 1101 -> "block007_bgcolor_003_001.png";
            case 1102 -> "block007_bgcolor_004_001.png";
            case 1103 -> "block007_bgcolor_005_001.png";
            case 1104 -> "block007_bgcolor_006_001.png";
            case 1105 -> "block007_bgcolor_007_001.png";
            case 1106 -> "block007_bgcolor_008_001.png";
            case 1107 -> "block007_bgcolor_009_001.png";
            case 1108 -> "block007_slope_02_001.png";
            case 1109 -> "block007_bgcolor_001_001.png";
            case 1110 -> "block007_bgcolor_012_001.png";
            case 1111 -> "block007_bgcolor_013_001.png";
            case 1112 -> "block007b_bgcolor_01_001.png";
            case 1113 -> "block007b_bgcolor_02_001.png";
            case 1114 -> "block007b_bgcolor_03_001.png";
            case 1115 -> "block007b_bgcolor_05_001.png";
            case 1116 -> "block007b_bgcolor_06_001.png";
            case 1117 -> "block007b_bgcolor_07_001.png";
            case 1118 -> "block007b_bgcolor_08_001.png";
            case 1120 -> "block008_topcolor_01_001.png";
            case 1122 -> "block008_topcolor_29_001.png";
            case 1123 -> "block008_topcolor_15_001.png";
            case 1124 -> "block008_topcolor_16_001.png";
            case 1125 -> "block008_topcolor_18_001.png";
            case 1126 -> "block008_topcolor_24b_001.png";
            case 1127 -> "block008_topcolor_24b_001.png";
            case 1132 -> "block008_topcolor_22_001.png";
            case 1133 -> "block008_topcolor_23_001.png";
            case 1134 -> "block008_topcolor_25_001.png";
            case 1135 -> "block008_topcolor_26_001.png";
            case 1136 -> "block008_topcolor_27_001.png";
            case 1137 -> "block008_topcolor_28_001.png";
            case 1138 -> "block008_topcolor_12_001.png";
            case 1139 -> "block008_topcolor_13_001.png";
            case 1140 -> "square_g_03_001.png";
            case 1141 -> "square_g_04_001.png";
            case 1142 -> "square_g_05_001.png";
            case 1143 -> "square_g_06_001.png";
            case 1144 -> "square_g_07_001.png";
            case 1145 -> "square_g_08_001.png";
            case 1146 -> "square_g_09_001.png";
            case 1147 -> "square_g_10_001.png";
            case 1148 -> "square_g_11_001.png";
            case 1149 -> "square_g_12_001.png";
            case 1150 -> "square_g_13_001.png";
            case 1151 -> "square_g_14_001.png";
            case 1152 -> "square_g_15_001.png";
            case 1153 -> "square_g_16_001.png";
            case 1154 -> "smallOutline_01_001.png";
            case 1155 -> "smallOutline_02_001.png";
            case 1156 -> "smallOutline_03_001.png";
            case 1157 -> "smallOutline_04_001.png";
            case 1158 -> "smallOutline_05_001.png";
            case 1159 -> "block009b_08_001.png";
            case 1160 -> "block009b_09_001.png";
            case 1161 -> "block009b_10_001.png";
            case 1162 -> "block006_01_001.png";
            case 1163 -> "block006_02_001.png";
            case 1164 -> "block006_03_001.png";
            case 1165 -> "block006_04_001.png";
            case 1166 -> "block006_05_001.png";
            case 1167 -> "block006_06_001.png";
            case 1168 -> "block006_07_001.png";
            case 1169 -> "block006_08_001.png";
            case 1170 -> "block006_09_001.png";
            case 1171 -> "block006_10_001.png";
            case 1172 -> "block006_11_001.png";
            case 1173 -> "block006_12_001.png";
            case 1174 -> "block006_13_001.png";
            case 1175 -> "block006_14_001.png";
            case 1176 -> "block006_15_001.png";
            case 1177 -> "block006_16_001.png";
            case 1178 -> "block006_17_001.png";
            case 1179 -> "block006_18_001.png";
            case 1180 -> "block006_19_001.png";
            case 1181 -> "block006_20_001.png";
            case 1182 -> "block006_21_001.png";
            case 1183 -> "block006_22_001.png";
            case 1184 -> "block006_23_001.png";
            case 1185 -> "block006_24_001.png";
            case 1186 -> "block006_25_001.png";
            case 1187 -> "block006_slope_01_001.png";
            case 1188 -> "block006_slope_02_001.png";
            case 1189 -> "block006_slope_square_01_001.png";
            case 1190 -> "block006_slope_square_02_001.png";
            case 1191 -> "block006_color_01_001.png";
            case 1192 -> "block006_color_02_001.png";
            case 1193 -> "block006_color_03_001.png";
            case 1194 -> "block006_color_04_001.png";
            case 1195 -> "block006_color_05_001.png";
            case 1196 -> "block006_color_06_001.png";
            case 1197 -> "block006_color_01_001.png";
            case 1198 -> "block006_slope_01_color_001.png";
            case 1199 -> "block006_slope_02_color_001.png";
            case 1200 -> "block006_slope_square_01_color_001.png";
            case 1201 -> "block006_slope_square_02_color_001.png";
            case 1202 -> "blockOutlineThick_01_001.png";
            case 1203 -> "blockOutlineThick_02_001.png";
            case 1204 -> "blockOutlineThick_03_001.png";
            case 1205 -> "blockOutlineThick_04_001.png";
            case 1206 -> "blockOutlineThick_05_001.png";
            case 1207 -> "blockOutlineThick_06_001.png";
            case 1208 -> "blockOutlineThick_07_001.png";
            case 1209 -> "blockOutlineThick_08_001.png";
            case 1210 -> "blockOutlineThick_03_001.png";
            case 1220 -> "blockOutlineThickb_01_001.png";
            case 1221 -> "blockOutlineThickb_02_001.png";
            case 1222 -> "blockOutlineThickb_03_001.png";
            case 1223 -> "blockOutlineThickb_04_001.png";
            case 1224 -> "blockOutlineThickb_05_001.png";
            case 1225 -> "blockOutlineThickb_06_001.png";
            case 1226 -> "blockOutlineThickb_08_001.png";
            case 1227 -> "blockOutlineThickb_03_001.png";
            case 1228 -> "d_waveBG_001.png";
            case 1229 -> "block010_piece_01_001.png";
            case 1230 -> "block010_piece_01_001.png";
            case 1231 -> "block010_piece_01_001.png";
            case 1232 -> "block010_piece_02_001.png";
            case 1233 -> "block010_piece_02_001.png";
            case 1234 -> "block010_piece_02_001.png";
            case 1235 -> "block010_piece_02_001.png";
            case 1236 -> "block010_piece_02_001.png";
            case 1237 -> "block010_piece_02_001.png";
            case 1238 -> "block010_piece_01_001.png";
            case 1239 -> "block010_02_001.png";
            case 1240 -> "block010_06_001.png";
            case 1241 -> "block008_topcolor_02_001.png";
            case 1242 -> "block008_topcolor_06_001.png";
            case 1243 -> "block008_topcolor_07_001.png";
            case 1244 -> "block008_topcolor_08_001.png";
            case 1245 -> "block008_topcolor_10_001.png";
            case 1246 -> "block008_topcolor_11_001.png";
            case 1247 -> "block009c_base_001.png";
            case 1248 -> "block009c_base_001.png";
            case 1249 -> "block009c_base_001.png";
            case 1250 -> "block009c_base_001.png";
            case 1251 -> "block009c_10_001.png";
            case 1252 -> "block009c_11_001.png";
            case 1253 -> "block009c_base_001.png";
            case 1254 -> "block009c_base_001.png";
            case 1255 -> "block009c_base_001.png";
            case 1256 -> "block009c_slope_03_001.png";
            case 1257 -> "block009c_slope_04_001.png";
            case 1258 -> "block009c_slope_03_001.png";
            case 1259 -> "block009c_slope_04_001.png";
            case 1260 -> "blockOutline_02_001.png";
            case 1261 -> "blockOutlineOuter1_01_001.png";
            case 1262 -> "blockOutlineThick_01_001.png";
            case 1263 -> "blockOutlineOuter2_01_001.png";
            case 1264 -> "blockOutlineThickb_01_001.png";
            case 1265 -> "blockOutlineOuter3_01_001.png";
            case 1266 -> "block009_07_001.png";
            case 1267 -> "block009_08_001.png";
            case 1268 -> "edit_eSpawnBtn_001.png";
            case 1269 -> "d_gradient_c_04_001.png";
            case 1270 -> "d_gradient_c_05_001.png";
            case 1271 -> "d_gradient_b_04_001.png";
            case 1272 -> "d_gradient_b_05_001.png";
            case 1273 -> "d_gradient_04_001.png";
            case 1274 -> "d_gradient_05_001.png";
            case 1275 -> "d_key01_001.png";
            case 1276 -> "d_keyHole01_001.png";
            case 1277 -> "block009c_base_001.png";
            case 1278 -> "block009c_base_001.png";
            case 1279 -> "block009c_base_001.png";
            case 1280 -> "block009c_base_001.png";
            case 1281 -> "block009c_base_001.png";
            case 1282 -> "block009c_base_001.png";
            case 1283 -> "block009c_base_001.png";
            case 1284 -> "block009c_base_001.png";
            case 1285 -> "block009c_base_001.png";
            case 1286 -> "block009c_base_001.png";
            case 1287 -> "block009c_base_001.png";
            case 1288 -> "block009c_base_001.png";
            case 1289 -> "block009c_base_001.png";
            case 1290 -> "block009c_base_001.png";
            case 1291 -> "d_gradient_06_001.png";
            case 1292 -> "d_gradient_b_06_001.png";
            case 1293 -> "d_gradient_c_06_001.png";
            case 1294 -> "block005_02b_001.png";
            case 1295 -> "block005_04b_001.png";
            case 1296 -> "block005b_02b_001.png";
            case 1297 -> "block005b_04b_001.png";
            case 1298 -> "block003_part01_001.png";
            case 1299 -> "block005c_02_001.png";
            case 1300 -> "block005c_03_001.png";
            case 1301 -> "block005c_04_001.png";
            case 1302 -> "block005c_10_001.png";
            case 1303 -> "block005c_11_001.png";
            case 1304 -> "block005c_06_001.png";
            case 1305 -> "block005c_slope_01_001.png";
            case 1306 -> "block005c_slope_02_001.png";
            case 1307 -> "block005c_slope_square_01_001.png";
            case 1308 -> "block005c_slope_square_02_001.png";
            case 1309 -> "block005c_slope_square_03_001.png";
            case 1310 -> "block005c_02_001.png";
            case 1311 -> "block005c_03_001.png";
            case 1312 -> "block005c_04_001.png";
            case 1313 -> "block005c_10_001.png";
            case 1314 -> "block005c_11_001.png";
            case 1315 -> "block005c_06_001.png";
            case 1316 -> "block005c_slope_01_001.png";
            case 1317 -> "block005c_slope_02_001.png";
            case 1318 -> "block005c_slope_square_01_001.png";
            case 1319 -> "block005c_slope_square_02_001.png";
            case 1320 -> "block005c_slope_square_03_001.png";
            case 1322 -> "block006_26_001.png";
            case 1325 -> "block006_slope_square_03_001.png";
            case 1326 -> "block006_slope_square_04_001.png";
            case 1327 -> "GJBeast02_01_001.png";
            case 1328 -> "GJBeast03_01_001.png";
            case 1329 -> "secretCoin_2_01_001.png";
            case 1330 -> "dropRing_01_001.png";
            case 1331 -> "portal_17_front_001.png";
            case 1332 -> "bump_02_001.png";
            case 1333 -> "ring_02_001.png";
            case 1334 -> "boost_05_001.png";
            case 1338 -> "blockOutline_14_001.png";
            case 1339 -> "blockOutline_15_001.png";
            case 1340 -> "invisibleOutline_01_001.png";
            case 1341 -> "blockOutline_14_001.png";
            case 1342 -> "blockOutline_15_001.png";
            case 1343 -> "invisibleOutline_b_01_001.png";
            case 1344 -> "blockOutline_14_001.png";
            case 1345 -> "blockOutline_15_001.png";
            case 1346 -> "edit_eRotateComBtn_001.png";
            case 1347 -> "edit_eFollowComBtn_001.png";
            case 1348 -> "block011_01_001.png";
            case 1349 -> "block011_02_001.png";
            case 1350 -> "block011_03_001.png";
            case 1351 -> "block011_04_001.png";
            case 1352 -> "block011b_01_001.png";
            case 1353 -> "block011b_02_001.png";
            case 1354 -> "block011b_03_001.png";
            case 1355 -> "block011b_04_001.png";
            case 1356 -> "block011_edge_02_001.png";
            case 1357 -> "block011_edge_03_001.png";
            case 1358 -> "block011_edge_04_001.png";
            case 1359 -> "block011_edge_05_001.png";
            case 1360 -> "block011_edge_06_001.png";
            case 1361 -> "block011_edge_07_001.png";
            case 1362 -> "block011_edge_08_001.png";
            case 1363 -> "block011_edge_09_001.png";
            case 1364 -> "block011_edge_10_001.png";
            case 1365 -> "block011_edge_11_001.png";
            case 1366 -> "block011_edge_12_001.png";
            case 1367 -> "block011_light_01_001.png";
            case 1368 -> "block011_light_02_001.png";
            case 1369 -> "block011_light_03_001.png";
            case 1370 -> "block011_light_04_001.png";
            case 1371 -> "block011_light_05_001.png";
            case 1372 -> "block011_light_06_001.png";
            case 1373 -> "block011_light_07_001.png";
            case 1374 -> "block011_light_08_001.png";
            case 1375 -> "block011_light_09_001.png";
            case 1376 -> "block011_light_10_001.png";
            case 1377 -> "block011_light_11_001.png";
            case 1378 -> "block011_light_12_001.png";
            case 1379 -> "block011_light_13_001.png";
            case 1380 -> "block011_light_14_001.png";
            case 1381 -> "block011_light_15_001.png";
            case 1382 -> "block011_light_16_001.png";
            case 1383 -> "block011_light_17_001.png";
            case 1384 -> "block011_light_18_001.png";
            case 1385 -> "block011_light_19_001.png";
            case 1386 -> "block011_light_20_001.png";
            case 1387 -> "block011b_piece_01_001.png";
            case 1388 -> "block011b_piece_02_001.png";
            case 1389 -> "block011b_piece_03_001.png";
            case 1390 -> "block011b_piece_04_001.png";
            case 1391 -> "block011b_piece_05_001.png";
            case 1392 -> "block011b_piece_06_001.png";
            case 1393 -> "block011b_piece_07_001.png";
            case 1394 -> "block011b_piece_08_001.png";
            case 1395 -> "block011_edge_01_001.png";
            case 1431 -> "block012_01_001.png";
            case 1432 -> "block012_02_001.png";
            case 1433 -> "block012_03_001.png";
            case 1434 -> "block012_04_001.png";
            case 1435 -> "block012_05_001.png";
            case 1436 -> "block012_06_001.png";
            case 1437 -> "block012_07_001.png";
            case 1438 -> "block012_08_001.png";
            case 1439 -> "block012_09_001.png";
            case 1440 -> "block012_10_001.png";
            case 1441 -> "block012_11_001.png";
            case 1442 -> "block012b_01_001.png";
            case 1443 -> "block012b_02_001.png";
            case 1444 -> "block012b_03_001.png";
            case 1445 -> "block012b_04_001.png";
            case 1446 -> "block012b_05_001.png";
            case 1447 -> "block012b_06_001.png";
            case 1448 -> "block012b_07_001.png";
            case 1449 -> "block012b_08_001.png";
            case 1450 -> "block012b_09_001.png";
            case 1451 -> "block012b_10_001.png";
            case 1452 -> "block012b_11_001.png";
            case 1453 -> "block012_light_01_001.png";
            case 1454 -> "block012_light_02_001.png";
            case 1455 -> "block012_light_03_001.png";
            case 1456 -> "block012_light_04_001.png";
            case 1457 -> "block012_light_05_001.png";
            case 1458 -> "block012_light_06_001.png";
            case 1459 -> "block012_light_07_001.png";
            case 1460 -> "block012_light_08_001.png";
            case 1461 -> "block013_01c_001.png";
            case 1462 -> "block013_02c_001.png";
            case 1463 -> "block013_03c_001.png";
            case 1464 -> "block013_04c_001.png";
            case 1471 -> "block013_detail_01_001.png";
            case 1472 -> "block013_detail_02_001.png";
            case 1473 -> "block013_detail_03_001.png";
            case 1496 -> "block013_detail_04_001.png";
            case 1507 -> "block013_detail_05_001.png";
            case 1510 -> "block012_12_001.png";
            case 1511 -> "block012_13_001.png";
            case 1512 -> "block012_14_001.png";
            case 1513 -> "block012b_12_001.png";
            case 1514 -> "block012b_13_001.png";
            case 1515 -> "block012b_14_001.png";
            case 1516 -> "waterfallAnim_001.png";
            case 1517 -> "waterfallAnim_007.png";
            case 1518 -> "waterSplash_001.png";
            case 1519 -> "starAnim_004.png";
            case 1520 -> "edit_eShakeBtn_001.png";
            case 1521 -> "d_rotatingLine_01_001.png";
            case 1522 -> "d_rotatingLine_02_001.png";
            case 1523 -> "d_rotatingLine_03_001.png";
            case 1524 -> "d_rotatingLine_04_001.png";
            case 1525 -> "d_rotatingSquare_01_001.png";
            case 1526 -> "d_rotatingSquare_02_001.png";
            case 1527 -> "d_rotatingSquare_03_001.png";
            case 1528 -> "d_rotatingSquare_04_001.png";
            case 1529 -> "persp_outline_01_001.png";
            case 1530 -> "persp_block013_09_001.png";
            case 1531 -> "persp_outline_03_001.png";
            case 1532 -> "persp_outline_04_001.png";
            case 1533 -> "persp_outline_05_001.png";
            case 1534 -> "persp_outline_06_001.png";
            case 1535 -> "persp_outline_07_001.png";
            case 1536 -> "persp_outline_08_001.png";
            case 1537 -> "persp_outline_09_001.png";
            case 1538 -> "persp_outline_01_001.png";
            case 1539 -> "persp_outline_02_001.png";
            case 1540 -> "persp_outline_06_001.png";
            case 1552 -> "persp_outline_01_001.png";
            case 1553 -> "persp_outline_02_001.png";
            case 1554 -> "persp_outline_03_001.png";
            case 1555 -> "persp_outline_04_001.png";
            case 1556 -> "persp_outline_05_001.png";
            case 1557 -> "persp_outline_06_001.png";
            case 1558 -> "persp_outline_07_001.png";
            case 1559 -> "persp_outline_08_001.png";
            case 1560 -> "persp_outline_09_001.png";
            case 1561 -> "persp_outline_01_001.png";
            case 1562 -> "persp_outline_02_001.png";
            case 1563 -> "persp_outline_03_001.png";
            case 1564 -> "persp_outline_04_001.png";
            case 1565 -> "persp_outline_05_001.png";
            case 1566 -> "persp_outline_06_001.png";
            case 1567 -> "persp_outline_07_001.png";
            case 1568 -> "persp_outline_08_001.png";
            case 1569 -> "persp_outline_09_001.png";
            case 1582 -> "fireball_01_001.png";
            case 1583 -> "fireball_02_001.png";
            case 1584 -> "GJBeast04_01_001.png";
            case 1585 -> "edit_eAnimateBtn_001.png";
            case 1586 -> "edit_eParticleBtn_001.png";
            case 1587 -> "d_heart01_001.png";
            case 1588 -> "d_heart01_match_001.png";
            case 1589 -> "d_potion01_001.png";
            case 1590 -> "d_potion01_match_001.png";
            case 1591 -> "lava_top_001.png";
            case 1592 -> "d_animSquare_01_001.png";
            case 1593 -> "lava_top_001.png";
            case 1594 -> "ring_custom_01_001.png";
            case 1595 -> "edit_eTouchBtn_001.png";
            case 1596 -> "d_skull_01_001.png";
            case 1597 -> "d_skull_02_001.png";
            case 1598 -> "d_skull01_001.png";
            case 1599 -> "d_skull01_match_001.png";
            case 1600 -> "d_sign_01_001.png";
            case 1601 -> "d_sign_pole_001.png";
            case 1602 -> "d_sign_img_01_001.png";
            case 1603 -> "d_sign_img_02_001.png";
            case 1604 -> "d_sign_img_03_001.png";
            case 1605 -> "d_sign_img_04_001.png";
            case 1606 -> "d_sign_img_05_001.png";
            case 1607 -> "d_sign_img_06_001.png";
            case 1608 -> "d_sign_paint_01_001.png";
            case 1609 -> "d_sign_paint_02_001.png";
            case 1610 -> "d_sign_paint_03_001.png";
            case 1611 -> "edit_eCountBtn_001.png";
            case 1612 -> "edit_ePHideBtn_001.png";
            case 1613 -> "edit_ePShowBtn_001.png";
            case 1614 -> "smallCoin_01_001.png";
            case 1615 -> "edit_eCounterBtn_001.png";
            case 1616 -> "edit_eStopMoverBtn_001.png";
            case 1617 -> "block013_detail_06_001.png";
            case 1618 -> "explosion_01_001.png";
            case 1619 -> "spinBlade01_001.png";
            case 1620 -> "spinBlade02_001.png";
            case 1621 -> "block013_edge_01_001.png";
            case 1622 -> "block013_edge_02_001.png";
            case 1623 -> "block013_edge_03_001.png";
            case 1624 -> "block013_edge_04_001.png";
            case 1625 -> "block013_edge_05_001.png";
            case 1626 -> "block013_edge_06_001.png";
            case 1627 -> "block013_edge_07_001.png";
            case 1628 -> "block013_edge_08_001.png";
            case 1629 -> "block013_edge_09_001.png";
            case 1630 -> "block013_edge_10_001.png";
            case 1631 -> "block013_edge_11_001.png";
            case 1632 -> "block013_edge_12_001.png";
            case 1633 -> "block013_edge_13_001.png";
            case 1634 -> "block013_edge_14_001.png";
            case 1635 -> "block013_edge_15_001.png";
            case 1636 -> "block013_edge_16_001.png";
            case 1637 -> "block013_edge_c_01_001.png";
            case 1638 -> "block013_edge_c_02_001.png";
            case 1639 -> "block013_edge_c_03_001.png";
            case 1640 -> "block013_edge_c_04_001.png";
            case 1641 -> "block013_edge_c_05_001.png";
            case 1642 -> "block013_edge_c_06_001.png";
            case 1643 -> "block013_edge_c_07_001.png";
            case 1644 -> "block013_edge_c_08_001.png";
            case 1645 -> "block013_edge_c_09_001.png";
            case 1646 -> "block013_edge_c_10_001.png";
            case 1647 -> "block013_edge_c_11_001.png";
            case 1648 -> "block013_edge_c_12_001.png";
            case 1649 -> "block013_edge_c_13_001.png";
            case 1650 -> "block013_edge_c_14_001.png";
            case 1651 -> "block013_edge_c_15_001.png";
            case 1652 -> "block013_edge_c_16_001.png";
            case 1653 -> "block013_light_01_001.png";
            case 1654 -> "block013_light_02_001.png";
            case 1655 -> "block013_light_03_001.png";
            case 1656 -> "block013_light_04_001.png";
            case 1657 -> "block013_light_05_001.png";
            case 1658 -> "block013_light_06_001.png";
            case 1659 -> "block013_light_07_001.png";
            case 1660 -> "block013_light_08_001.png";
            case 1661 -> "block013_light_09_001.png";
            case 1662 -> "block013_light_10_001.png";
            case 1663 -> "block013_light_11_001.png";
            case 1664 -> "block013_light_12_001.png";
            case 1665 -> "block013_light_13_001.png";
            case 1666 -> "block013_light_14_001.png";
            case 1667 -> "block013_light_15_001.png";
            case 1668 -> "block013_light_16_001.png";
            case 1669 -> "block013_light_c_01_001.png";
            case 1670 -> "block013_light_c_02_001.png";
            case 1671 -> "block013_light_c_03_001.png";
            case 1672 -> "block013_light_c_04_001.png";
            case 1673 -> "block013_light_c_05_001.png";
            case 1674 -> "block013_light_c_06_001.png";
            case 1675 -> "block013_light_c_07_001.png";
            case 1676 -> "block013_light_c_08_001.png";
            case 1677 -> "block013_light_c_09_001.png";
            case 1678 -> "block013_light_c_10_001.png";
            case 1679 -> "block013_light_c_11_001.png";
            case 1680 -> "block013_light_c_12_001.png";
            case 1681 -> "block013_light_c_13_001.png";
            case 1682 -> "block013_light_c_14_001.png";
            case 1683 -> "block013_light_c_15_001.png";
            case 1684 -> "block013_light_c_16_001.png";
            case 1685 -> "puzzle_base_001.png";
            case 1686 -> "puzzle_base_001.png";
            case 1687 -> "puzzle_base_001.png";
            case 1688 -> "puzzle_base_001.png";
            case 1689 -> "puzzle_base_001.png";
            case 1690 -> "puzzle_base_001.png";
            case 1691 -> "puzzle_base_001.png";
            case 1692 -> "puzzle_base_001.png";
            case 1693 -> "puzzle_base_001.png";
            case 1694 -> "puzzle_base_001.png";
            case 1695 -> "puzzle_base_001.png";
            case 1696 -> "puzzle_base_001.png";
            case 1697 -> "d_zag_01_001.png";
            case 1698 -> "d_zag_02_001.png";
            case 1699 -> "d_zag_03_001.png";
            case 1700 -> "edit_eParticleBtn_001.png";
            case 1701 -> "bladeTrap01_001.png";
            case 1702 -> "bladeTrap02_001.png";
            case 1703 -> "bladeTrap03_001.png";
            case 1704 -> "dashRing_01_001.png";
            case 1705 -> "sawblade_01_001.png";
            case 1706 -> "sawblade_02_001.png";
            case 1707 -> "sawblade_03_001.png";
            case 1708 -> "darkblade_01_001.png";
            case 1709 -> "darkblade_02_001.png";
            case 1710 -> "darkblade_03_001.png";
            case 1711 -> "pit_b_01_001.png";
            case 1712 -> "pit_b_02_001.png";
            case 1713 -> "pit_b_03_001.png";
            case 1714 -> "pit_b_04_001.png";
            case 1715 -> "pit_01_001.png";
            case 1716 -> "pit_01_low_001.png";
            case 1717 -> "pit_01_slope_01_001.png";
            case 1718 -> "pit_01_slope_02_001.png";
            case 1719 -> "pit_04_001.png";
            case 1720 -> "pit_04_02_001.png";
            case 1721 -> "pit_04_03_001.png";
            case 1722 -> "pit_04_low_001.png";
            case 1723 -> "pit_04_slope_01_001.png";
            case 1724 -> "pit_04_slope_02_001.png";
            case 1725 -> "pit_05_001.png";
            case 1726 -> "pit_05_02_001.png";
            case 1727 -> "pit_05_03_001.png";
            case 1728 -> "pit_06_001.png";
            case 1729 -> "pit_06_2_001.png";
            case 1730 -> "pit_07_001.png";
            case 1731 -> "pit_07_2_001.png";
            case 1732 -> "pit_07_3_001.png";
            case 1733 -> "pit_07_4_001.png";
            case 1734 -> "blackCogwheel_01_001.png";
            case 1735 -> "blackCogwheel_02_001.png";
            case 1736 -> "blackCogwheel_03_001.png";
            case 1737 -> "d_pixelArt01_001_001.png";
            case 1738 -> "d_pixelArt01_002_001.png";
            case 1739 -> "d_pixelArt01_003_001.png";
            case 1740 -> "d_pixelArt01_004_001.png";
            case 1741 -> "d_pixelArt01_005_001.png";
            case 1742 -> "d_pixelArt01_006_001.png";
            case 1743 -> "triangle_a_02_001.png";
            case 1744 -> "triangle_a_04_001.png";
            case 1745 -> "triangle_c_02_001.png";
            case 1746 -> "triangle_c_04_001.png";
            case 1747 -> "lighttriangle_01_02_color_001.png";
            case 1748 -> "lighttriangle_01_04_color_001.png";
            case 1749 -> "triangle_f_02_001.png";
            case 1750 -> "triangle_f_04_001.png";
            case 1751 -> "dashRing_02_001.png";
            case 1752 -> "d_ringSpiral_01_001.png";
            case 1753 -> "gridLine01_001.png";
            case 1754 -> "gridLine02_001.png";
            case 1755 -> "blockOutline_01_001.png";
            case 1756 -> "d_link_b_06_001.png";
            case 1757 -> "gridLine03_001.png";
            case 1758 -> "blockOutline_14_001.png";
            case 1759 -> "blockOutline_15_001.png";
            case 1760 -> "blockOutline_14_001.png";
            case 1761 -> "blockOutline_15_001.png";
            case 1762 -> "blockOutline_14_001.png";
            case 1763 -> "blockOutline_15_001.png";
            case 1764 -> "d_small_ball_01_001.png";
            case 1765 -> "d_small_ball_02_001.png";
            case 1766 -> "d_small_ball_03_001.png";
            case 1767 -> "d_small_ball_04_001.png";
            case 1768 -> "d_small_ball_05_001.png";
            case 1769 -> "block013_01c_001.png";
            case 1770 -> "block013_03c_001.png";
            case 1771 -> "block013_02c_001.png";
            case 1772 -> "block013_04c_001.png";
            case 1773 -> "blockOutline_15_001.png";
            case 1774 -> "blockOutline_15_001.png";
            case 1775 -> "blockOutline_15_001.png";
            case 1776 -> "blockOutline_15_001.png";
            case 1777 -> "block011_01_001.png";
            case 1778 -> "block011_02_001.png";
            case 1779 -> "block011_03_001.png";
            case 1780 -> "block011_04_001.png";
            case 1781 -> "block011b_01_001.png";
            case 1782 -> "block011b_02_001.png";
            case 1783 -> "block011b_03_001.png";
            case 1784 -> "block011b_04_001.png";
            case 1785 -> "blockOutline_15_001.png";
            case 1786 -> "blockOutline_15_001.png";
            case 1787 -> "blockOutline_15_001.png";
            case 1788 -> "blockOutline_15_001.png";
            case 1789 -> "blockOutline_15_001.png";
            case 1790 -> "blockOutline_15_001.png";
            case 1791 -> "blockOutline_15_001.png";
            case 1792 -> "blockOutline_15_001.png";
            case 1793 -> "block012_03_001.png";
            case 1794 -> "blockOutline_15_001.png";
            case 1795 -> "block012_12_001.png";
            case 1796 -> "blockOutline_15_001.png";
            case 1797 -> "puzzle_base_001.png";
            case 1798 -> "blockOutline_15_001.png";
            case 1799 -> "block012b_03_001.png";
            case 1800 -> "blockOutline_15_001.png";
            case 1801 -> "block012b_12_001.png";
            case 1802 -> "blockOutline_15_001.png";
            case 1803 -> "block012_01_001.png";
            case 1804 -> "blockOutline_15_001.png";
            case 1805 -> "block012b_01_001.png";
            case 1806 -> "blockOutline_15_001.png";
            case 1807 -> "block012_13_001.png";
            case 1808 -> "blockOutline_15_001.png";
            case 1809 -> "block012b_13_001.png";
            case 1810 -> "blockOutline_15_001.png";
            case 1811 -> "edit_eInstantCountBtn_001.png";
            case 1812 -> "edit_eOnDeathBtn_001.png";
            case 1813 -> "blockOutline_01_001.png";
            case 1814 -> "edit_eFollowPComBtn_001.png";
            case 1815 -> "edit_eCollisionBtn_001.png";
            case 1816 -> "edit_eCollisionBlock01_001.png";
            case 1817 -> "edit_ePickupBtn_001.png";
            case 1818 -> "edit_eBGEOn_001.png";
            case 1819 -> "edit_eBGEOff_001.png";
            case 1820 -> "lightsquare_02_01_color_001.png";
            case 1821 -> "lightsquare_02_02_color_001.png";
            case 1823 -> "lightsquare_02_03_color_001.png";
            case 1824 -> "lightsquare_02_04_color_001.png";
            case 1825 -> "lightsquare_02_05_color_001.png";
            case 1826 -> "lightsquare_02_06_color_001.png";
            case 1827 -> "lightsquare_02_07_color_001.png";
            case 1828 -> "lightsquare_02_08_color_001.png";
            case 1829 -> "blockOutline_01_001.png";
            case 1830 -> "gridLine04_001.png";
            case 1831 -> "d_scaleFadeRing_01_001.png";
            case 1832 -> "d_scaleFadeRing_03_001.png";
            case 1833 -> "d_scaleFadeRing_02_001.png";
            case 1834 -> "d_scaleFadeRing_04_001.png";
            case 1835 -> "d_scaleFadeRing_01_001.png";
            case 1836 -> "d_scaleFadeRing_03_001.png";
            case 1837 -> "d_scaleFadeRing_02_001.png";
            case 1838 -> "d_scaleFadeRing_04_001.png";
            case 1839 -> "d_scaleFadeRing_01_001.png";
            case 1840 -> "d_scaleFadeRing_03_001.png";
            case 1841 -> "d_scaleFadeRing_02_001.png";
            case 1842 -> "d_scaleFadeRing_04_001.png";
            case 1843 -> "d_sign_tile_01_001.png";
            case 1844 -> "gjHand_01_001.png";
            case 1845 -> "gjHand_02_001.png";
            case 1846 -> "gjHand_03_001.png";
            case 1847 -> "gjHand_04_001.png";
            case 1848 -> "gjHand_05_001.png";
            case 1849 -> "gj_smoke01_001.png";
            case 1850 -> "gj_smoke02_001.png";
            case 1851 -> "gj_drops01_001.png";
            case 1852 -> "gj_drops02_005.png";
            case 1853 -> "gj_drops03_001.png";
            case 1854 -> "gj_drops04_003.png";
            case 1855 -> "gj_drops05_001.png";
            case 1856 -> "gj_bubble01_005.png";
            case 1857 -> "gj_lightning01_001.png";
            case 1858 -> "gj_drops06_001.png";
            case 1859 -> "blockOutline_01_001.png";
            case 1860 -> "gj_lightning02_004.png";
            case 1861 -> "blockDesign01_01_001.png";
            case 1862 -> "blockDesign01_02_001.png";
            case 1863 -> "blockDesign01_03_001.png";
            case 1864 -> "blockDesign01_04_001.png";
            case 1865 -> "blockDesign02_01_001.png";
            case 1866 -> "blockDesign02_02_001.png";
            case 1867 -> "blockDesign02_03_001.png";
            case 1868 -> "blockDesign02_04_001.png";
            case 1869 -> "blockDesign03_01_001.png";
            case 1870 -> "blockDesign03_02_001.png";
            case 1871 -> "blockDesign03_03_001.png";
            case 1872 -> "blockDesign03_04_001.png";
            case 1873 -> "blockDesign04_01_001.png";
            case 1874 -> "blockDesign05_01_001.png";
            case 1875 -> "blockDesign05_02_001.png";
            case 1876 -> "blockDesign05_03_001.png";
            case 1877 -> "blockDesign05_04_001.png";
            case 1878 -> "blockDesign06_01_001.png";
            case 1879 -> "blockDesign06_02_001.png";
            case 1880 -> "blockDesign06_03_001.png";
            case 1881 -> "blockDesign06_04_001.png";
            case 1882 -> "blockDesign07_01_001.png";
            case 1883 -> "blockDesign07_02_001.png";
            case 1884 -> "blockDesign07_03_001.png";
            case 1885 -> "blockDesign07_04_001.png";
            case 1886 -> "emptyFrame.png";
            case 1887 -> "emptyFrame.png";
            case 1888 -> "emptyFrame.png";
            case 1889 -> "fakeSpike_01_001.png";
            case 1890 -> "fakeSpike_02_001.png";
            case 1891 -> "fakeSpike_03_001.png";
            case 1892 -> "fakeSpike_04_001.png";
            case 1893 -> "square_b_01_001.png";
            case 1894 -> "square_b_02_001.png";
            case 1895 -> "square_b_03_001.png";
            case 1896 -> "square_b_04_001.png";
            case 1897 -> "square_b_05_001.png";
            case 1898 -> "square_b_06_001.png";
            case 1899 -> "blockOutline_14_001.png";
            case 1900 -> "blockOutline_15_001.png";
            case 1901 -> "triangle_b_square_01_001.png";
            case 1902 -> "triangle_b_square_02_001.png";
            case 1903 -> "plank_01_001.png";
            case 1904 -> "plank_01_02_001.png";
            case 1905 -> "plank_01_03_001.png";
            case 1906 -> "blockOutline_14_001.png";
            case 1907 -> "blockOutline_15_001.png";
            case 1908 -> "plank_01_square_01_001.png";
            case 1909 -> "plank_01_square_02_001.png";
            case 1910 -> "square_01_small_001.png";
            case 1911 -> "plank_01_small_001.png";
            default -> null;
        };
    }

    @NotNull
    public static ZLayer defaultZLayerOfObjectID(final int objectID) {
        return switch (objectID) {
            case 5, 15, 16, 17, 691, 690, 689, 686, 685, 684, 683, 682, 681, 672, 671, 670, 669, 668, 659, 658, 657, 656, 655, 654, 653, 650, 649, 648, 647, 646, 645, 644, 643, 642, 641, 640, 639, 638, 637, 636, 635, 634, 633, 632, 631, 630, 629, 628, 627, 626, 625, 624, 623, 622, 621, 620, 619, 618, 617, 616, 615, 614, 613, 612, 611, 610, 609, 608, 607, 606, 605, 604, 603, 602, 601, 600, 599, 598, 597, 596, 595, 594, 593, 592, 591, 590, 589, 588, 587, 586, 585, 584, 583, 582, 581, 580, 579, 578, 577, 576, 575, 574, 573, 572, 571, 570, 569, 568, 567, 566, 565, 564, 563, 562, 561, 560, 559, 558, 557, 556, 555, 554, 553, 552, 551, 550, 549, 548, 547, 546, 545, 544, 543, 542, 541, 540, 539, 538, 537, 536, 535, 534, 533, 532, 531, 530, 529, 528, 527, 526, 525, 524, 523, 522, 521, 520, 519, 518, 517, 516, 515, 505, 504, 503, 502, 491, 490, 489, 488, 487, 486, 485, 482, 481, 480, 479, 478, 477, 476, 393, 358, 325, 324, 278, 277, 273, 266, 259, 251, 246, 245, 211, 203, 202, 201, 200, 199, 198, 193, 191, 164, 120, 80, 73, 1030, 1029, 1028, 1027, 1026, 1025, 1024, 1018, 1017, 1016, 1015, 1014, 1013, 1012, 1011, 1010, 1009, 1292, 988, 987, 986, 985, 984, 983, 982, 981, 980, 977, 976, 975, 974, 973, 972, 971, 970, 969, 968, 967, 966, 965, 964, 961, 960, 959, 958, 957, 956, 955, 954, 953, 952, 951, 950, 949, 948, 947, 946, 945, 944, 943, 935, 934, 933, 932, 931, 930, 929, 928, 927, 926, 925, 911, 905, 904, 903, 902, 896, 895, 894, 893, 891, 890, 889, 888, 885, 884, 883, 882, 881, 880, 878, 877, 874, 873, 872, 871, 870, 869, 868, 867, 863, 862, 861, 859, 857, 856, 855, 854, 853, 850, 848, 847, 846, 845, 844, 843, 842, 841, 833, 832, 831, 830, 829, 828, 827, 826, 825, 824, 823, 822, 821, 820, 819, 818, 817, 816, 815, 814, 813, 812, 811, 810, 809, 808, 807, 775, 774, 773, 772, 771, 770, 769, 766, 765, 764, 763, 762, 759, 758, 757, 756, 755, 754, 753, 752, 739, 738, 737, 736, 735, 734, 733, 732, 731, 730, 725, 724, 723, 722, 716, 715, 714, 713, 708, 707, 706, 705, 704, 703, 702, 701, 700, 699, 698, 697, 696, 695, 694, 693, 692, 1349, 1348, 1334, 1326, 1325, 1322, 1320, 1319, 1318, 1317, 1316, 1315, 1314, 1313, 1312, 1311, 1310, 1309, 1308, 1307, 1306, 1305, 1304, 1303, 1302, 1301, 1300, 1299, 1298, 1297, 1296, 1295, 1294, 1293, 1291, 1290, 1289, 1288, 1287, 1286, 1285, 1284, 1283, 1282, 1281, 1280, 1279, 1278, 1277, 1274, 1273, 1272, 1271, 1270, 1269, 1267, 1266, 1259, 1258, 1257, 1256, 1255, 1254, 1253, 1252, 1251, 1250, 1249, 1248, 1247, 1240, 1239, 1238, 1237, 1236, 1235, 1234, 1233, 1232, 1231, 1230, 1229, 1190, 1189, 1188, 1187, 1186, 1185, 1184, 1183, 1182, 1181, 1180, 1179, 1178, 1177, 1176, 1175, 1174, 1173, 1172, 1171, 1170, 1169, 1168, 1167, 1166, 1165, 1164, 1163, 1162, 1161, 1160, 1159, 1153, 1152, 1151, 1150, 1149, 1148, 1147, 1146, 1145, 1144, 1143, 1142, 1141, 1140, 1118, 1117, 1116, 1115, 1114, 1113, 1112, 1111, 1110, 1109, 1108, 1107, 1106, 1105, 1104, 1103, 1102, 1101, 1100, 1099, 1098, 1097, 1096, 1095, 1094, 1093, 1092, 1091, 1090, 1089, 1088, 1087, 1086, 1085, 1084, 1083, 1082, 1081, 1080, 1079, 1078, 1077, 1076, 1075, 1071, 1070, 1069, 1068, 1067, 1066, 1065, 1064, 1063, 1062, 1048, 1047, 1046, 1045, 1044, 1043, 1042, 1041, 1040, 1039, 1038, 1037, 1036, 1035, 1034, 1033, 1032, 1031, 1883, 1882, 1881, 1880, 1879, 1878, 1877, 1876, 1875, 1874, 1873, 1872, 1871, 1870, 1869, 1868, 1867, 1866, 1865, 1864, 1863, 1862, 1861, 1828, 1827, 1826, 1825, 1824, 1823, 1821, 1820, 1810, 1809, 1808, 1807, 1806, 1805, 1804, 1803, 1802, 1801, 1800, 1799, 1796, 1795, 1794, 1793, 1792, 1791, 1790, 1789, 1788, 1787, 1786, 1785, 1784, 1783, 1782, 1781, 1780, 1779, 1778, 1777, 1776, 1775, 1774, 1773, 1772, 1771, 1770, 1769, 1763, 1762, 1761, 1760, 1759, 1758, 1742, 1741, 1740, 1739, 1738, 1737, 1652, 1651, 1650, 1649, 1648, 1647, 1646, 1645, 1644, 1643, 1642, 1641, 1640, 1639, 1638, 1637, 1636, 1635, 1634, 1633, 1632, 1631, 1630, 1629, 1628, 1627, 1626, 1625, 1624, 1623, 1622, 1621, 1617, 1586, 1560, 1559, 1558, 1557, 1556, 1555, 1554, 1553, 1552, 1540, 1539, 1538, 1537, 1536, 1535, 1534, 1533, 1532, 1531, 1529, 1515, 1514, 1513, 1512, 1511, 1510, 1507, 1496, 1473, 1472, 1471, 1464, 1463, 1462, 1461, 1452, 1451, 1450, 1449, 1448, 1447, 1446, 1445, 1444, 1443, 1442, 1441, 1440, 1439, 1438, 1437, 1436, 1435, 1434, 1433, 1432, 1431, 1395, 1394, 1393, 1392, 1391, 1390, 1389, 1388, 1387, 1366, 1365, 1364, 1363, 1362, 1361, 1360, 1359, 1358, 1357, 1356, 1355, 1354, 1353, 1352, 1351, 1350, 1892, 1891, 1890, 1889, 1888, 1887, 1886, 1885, 1884 -> ZLayer.B2;
            case 18, 19, 20, 21, 35, 36, 1380, 1379, 1378, 1377, 1376, 1375, 1374, 1373, 1372, 1371, 1370, 1369, 1368, 1367, 1333, 1332, 1330, 1228, 1061, 1060, 1059, 1058, 1057, 1056, 1055, 1054, 1053, 1052, 1051, 1050, 1022, 1021, 1020, 1019, 1005, 1004, 1003, 1002, 1001, 1000, 999, 998, 997, 992, 990, 942, 941, 940, 939, 938, 937, 936, 917, 916, 910, 909, 908, 907, 906, 767, 721, 719, 688, 687, 514, 513, 512, 511, 510, 509, 508, 507, 506, 501, 500, 499, 498, 497, 496, 495, 494, 466, 465, 464, 463, 462, 461, 460, 457, 456, 455, 454, 453, 452, 451, 450, 449, 448, 420, 419, 414, 413, 412, 411, 410, 409, 408, 407, 406, 405, 396, 395, 394, 378, 377, 376, 375, 285, 284, 283, 282, 281, 280, 279, 242, 241, 240, 239, 238, 237, 236, 235, 234, 233, 232, 231, 230, 229, 228, 227, 226, 225, 224, 223, 222, 190, 182, 181, 180, 159, 158, 157, 156, 155, 154, 153, 152, 151, 150, 149, 148, 141, 140, 139, 138, 137, 136, 134, 133, 132, 131, 130, 129, 128, 127, 126, 125, 124, 123, 115, 114, 113, 110, 107, 106, 97, 87, 86, 85, 84, 67, 60, 54, 53, 52, 51, 50, 49, 48, 41, 1902, 1901, 1900, 1899, 1898, 1897, 1896, 1895, 1894, 1893, 1848, 1847, 1846, 1845, 1844, 1843, 1842, 1841, 1840, 1839, 1838, 1837, 1836, 1835, 1834, 1833, 1832, 1831, 1830, 1798, 1797, 1768, 1767, 1766, 1765, 1764, 1757, 1756, 1754, 1753, 1752, 1751, 1704, 1700, 1699, 1698, 1697, 1696, 1695, 1694, 1693, 1692, 1691, 1690, 1689, 1688, 1687, 1686, 1685, 1684, 1683, 1682, 1681, 1680, 1679, 1678, 1677, 1676, 1675, 1674, 1673, 1672, 1671, 1670, 1669, 1668, 1667, 1666, 1665, 1664, 1663, 1662, 1661, 1660, 1659, 1658, 1657, 1656, 1655, 1654, 1653, 1618, 1610, 1609, 1608, 1607, 1606, 1605, 1604, 1603, 1602, 1601, 1600, 1597, 1596, 1594, 1593, 1592, 1591, 1583, 1582, 1528, 1527, 1526, 1525, 1524, 1523, 1522, 1521, 1517, 1516, 1460, 1459, 1458, 1457, 1456, 1455, 1454, 1453, 1386, 1385, 1384, 1383, 1382, 1381 -> ZLayer.B1;
            default -> ZLayer.T1;
        };
    }

    public static int defaultZOrderOfObjectID(final int objectID) {
        return switch (objectID) {
            case 1352, 1353, 1354, 1355, 1387, 1388, 1389, 1390, 1391, 1392, 1393, 1394, 1442, 1443, 1444, 1445, 1446, 1447, 1448, 1449, 1450, 1451, 1452, 1513, 1514, 1515, 1781, 1782, 1783, 1784, 1789, 1790, 1791, 1792, 1799, 1800, 1801, 1802, 1805, 1806, 1809, 1810 -> -9;
            case 1099, 1100, 1101, 1102, 1103, 1104, 1105, 1106, 1107, 1108, 1109, 1110, 1111, 1112, 1113, 1114, 1115, 1116, 1117, 1118 -> -8;
            case 5, 73, 80, 120, 164, 193, 211, 245, 246, 251, 259, 266, 273, 277, 278, 324, 325, 358, 476, 477, 478, 479, 480, 481, 482, 485, 486, 487, 488, 489, 490, 491, 502, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 653, 654, 655, 656, 657, 658, 659, 668, 669, 670, 671, 672, 681, 682, 683, 684, 685, 686, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 713, 714, 715, 716, 722, 723, 724, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 752, 753, 754, 755, 756, 757, 758, 759, 762, 763, 764, 765, 766, 769, 770, 771, 772, 773, 774, 775, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 833, 841, 842, 843, 844, 845, 846, 847, 848, 850, 853, 854, 855, 856, 857, 859, 861, 862, 863, 867, 868, 869, 870, 871, 872, 873, 874, 877, 878, 880, 881, 882, 883, 884, 885, 888, 889, 890, 891, 893, 894, 895, 896, 903, 904, 905, 911, 927, 928, 929, 930, 931, 932, 933, 934, 935, 943, 944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 975, 976, 977, 980, 981, 982, 983, 984, 985, 986, 987, 988, 1014, 1015, 1016, 1017, 1018, 1024, 1025, 1026, 1027, 1028, 1029, 1030, 1031, 1032, 1033, 1034, 1035, 1036, 1037, 1038, 1039, 1040, 1041, 1042, 1043, 1044, 1045, 1046, 1047, 1048, 1062, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070, 1071, 1075, 1076, 1077, 1078, 1079, 1080, 1081, 1082, 1083, 1084, 1085, 1086, 1087, 1088, 1089, 1090, 1091, 1092, 1093, 1094, 1095, 1096, 1097, 1098, 1140, 1141, 1142, 1143, 1144, 1145, 1146, 1147, 1148, 1149, 1150, 1151, 1152, 1153, 1159, 1160, 1161, 1162, 1163, 1164, 1165, 1166, 1167, 1168, 1169, 1170, 1171, 1172, 1173, 1174, 1175, 1176, 1177, 1178, 1179, 1180, 1181, 1182, 1183, 1184, 1185, 1186, 1187, 1188, 1189, 1190, 1229, 1230, 1231, 1232, 1233, 1234, 1235, 1236, 1237, 1238, 1239, 1240, 1247, 1248, 1249, 1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258, 1259, 1266, 1267, 1277, 1278, 1279, 1280, 1281, 1282, 1283, 1284, 1285, 1286, 1287, 1288, 1289, 1290, 1294, 1295, 1296, 1297, 1298, 1299, 1300, 1301, 1302, 1303, 1304, 1305, 1306, 1307, 1308, 1309, 1310, 1311, 1312, 1313, 1314, 1315, 1316, 1317, 1318, 1319, 1320, 1322, 1325, 1326, 1348, 1349, 1350, 1351, 1356, 1357, 1358, 1359, 1360, 1361, 1362, 1363, 1364, 1365, 1366, 1395, 1431, 1432, 1433, 1434, 1435, 1436, 1437, 1438, 1439, 1440, 1441, 1461, 1462, 1463, 1464, 1471, 1472, 1473, 1496, 1507, 1510, 1511, 1512, 1529, 1531, 1532, 1533, 1534, 1535, 1536, 1537, 1538, 1539, 1540, 1552, 1553, 1554, 1555, 1556, 1557, 1558, 1559, 1560, 1617, 1621, 1622, 1623, 1624, 1625, 1626, 1627, 1628, 1629, 1630, 1631, 1632, 1633, 1634, 1635, 1636, 1637, 1638, 1639, 1640, 1641, 1642, 1643, 1644, 1645, 1646, 1647, 1648, 1649, 1650, 1651, 1652, 1737, 1738, 1739, 1740, 1741, 1742, 1769, 1770, 1771, 1772, 1773, 1774, 1775, 1776, 1777, 1778, 1779, 1780, 1785, 1786, 1787, 1788, 1793, 1794, 1795, 1796, 1803, 1804, 1807, 1808, 1820, 1821, 1823, 1824, 1825, 1826, 1827, 1828, 1861, 1862, 1863, 1864, 1865, 1866, 1867, 1868, 1869, 1870, 1871, 1872, 1873, 1874, 1875, 1876, 1877, 1878, 1879, 1880, 1881, 1882, 1883, 1884, 1885 -> -7;
            case 15, 16, 17, 200, 201, 202, 203, 506, 507, 508, 509, 510, 511, 512, 513, 514, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 725, 902, 1334 -> -6;
            case 907, 908, 909, 910, 916, 917, 936, 937, 938, 939, 940, 941, 942, 1367, 1368, 1369, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378, 1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386, 1453, 1454, 1455, 1456, 1457, 1458, 1459, 1460, 1653, 1654, 1655, 1656, 1657, 1658, 1659, 1660, 1661, 1662, 1663, 1664, 1665, 1666, 1667, 1668, 1669, 1670, 1671, 1672, 1673, 1674, 1675, 1676, 1677, 1678, 1679, 1680, 1681, 1682, 1683, 1684, 1764, 1765, 1766, 1767, 1768 -> -5;
            case 191, 198, 199, 393, 1889, 1890, 1891, 1892 -> -4;
            case 678, 679, 680, 1586, 1700 -> 0;
            case 88, 89, 94, 98, 123, 124, 125, 126, 127, 128, 183, 184, 185, 186, 187, 188, 363, 364, 365, 366, 367, 368, 397, 398, 399, 406, 407, 408, 414, 421, 422, 446, 447, 667, 675, 676, 677, 720, 740, 741, 742, 768, 906, 914, 989, 991, 1596, 1597, 1615, 1619, 1620, 1701, 1702, 1703, 1705, 1706, 1707, 1708, 1709, 1710, 1716, 1717, 1718, 1722, 1723, 1724, 1725, 1726, 1727, 1728, 1729, 1730, 1731, 1732, 1733, 1734, 1735, 1736 -> 1;
            case 1, 2, 3, 4, 6, 7, 8, 9, 22, 23, 24, 25, 26, 27, 28, 29, 30, 34, 32, 33, 39, 40, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 68, 69, 70, 71, 72, 74, 75, 76, 77, 78, 81, 82, 83, 90, 91, 92, 93, 95, 96, 103, 915, 105, 116, 117, 118, 119, 121, 122, 135, 143, 144, 145, 146, 147, 160, 161, 162, 163, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 192, 194, 195, 196, 197, 204, 205, 206, 207, 208, 209, 210, 212, 213, 215, 216, 217, 218, 219, 220, 899, 243, 244, 247, 248, 249, 250, 252, 253, 254, 255, 256, 257, 258, 260, 261, 263, 264, 265, 267, 268, 269, 270, 271, 272, 274, 275, 289, 291, 294, 295, 296, 297, 299, 301, 305, 307, 309, 311, 315, 317, 321, 323, 326, 327, 328, 329, 331, 333, 337, 339, 343, 345, 349, 351, 353, 355, 369, 370, 371, 372, 373, 374, 392, 458, 459, 483, 484, 492, 493, 651, 652, 673, 674, 709, 710, 711, 712, 726, 727, 728, 729, 744, 886, 887, 900, 901, 918, 919, 920, 921, 923, 924, 925, 926, 1006, 1007, 1049, 1154, 1155, 1156, 1157, 1158, 1227, 1260, 1261, 1262, 1263, 1264, 1265, 1268, 1327, 1328, 1340, 1341, 1342, 1343, 1344, 1345, 1346, 1347, 1518, 1519, 1520, 1530, 1561, 1562, 1563, 1564, 1565, 1566, 1567, 1568, 1569, 1584, 1585, 1595, 1611, 1612, 1613, 1616, 1711, 1712, 1713, 1714, 1715, 1719, 1720, 1721, 1743, 1744, 1745, 1746, 1747, 1748, 1749, 1750, 1755, 1811, 1812, 1813, 1814, 1815, 1816, 1817, 1818, 1819, 1829, 1849, 1850, 1851, 1852, 1853, 1854, 1855, 1856, 1857, 1858, 1859, 1860, 1903, 1904, 1905, 1906, 1907, 1908, 1909, 1910, 1911 -> 2;
            case 467, 468, 469, 470, 471, 472, 473, 474, 475, 661, 662, 663, 664, 665, 666, 1202, 1203, 1204, 1205, 1206, 1207, 1208, 1209, 1210, 1220, 1221, 1222, 1223, 1224, 1225, 1226, 1338, 1339 -> 3;
            case 1120, 1122, 1123, 1124, 1125, 1126, 1127, 1132, 1133, 1134, 1135, 1136, 1137, 1138, 1139, 1241, 1242, 1243, 1244, 1245, 1246 -> 4;
            case 1601 -> 8;
            case 18, 19, 20, 21, 41, 48, 49, 50, 51, 52, 53, 54, 60, 85, 86, 87, 97, 106, 107, 110, 113, 114, 115, 129, 130, 131, 134, 137, 138, 139, 148, 149, 151, 152, 153, 154, 155, 156, 157, 158, 159, 180, 181, 182, 190, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 237, 238, 239, 240, 241, 242, 279, 280, 281, 282, 283, 284, 285, 375, 376, 377, 378, 394, 395, 396, 405, 409, 410, 411, 412, 413, 419, 420, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 461, 462, 463, 464, 465, 466, 498, 499, 500, 501, 503, 504, 505, 719, 721, 767, 990, 992, 997, 998, 999, 1000, 1001, 1002, 1003, 1004, 1005, 1292, 1009, 1010, 1011, 1012, 1013, 1019, 1020, 1021, 1050, 1051, 1052, 1053, 1054, 1055, 1056, 1057, 1058, 1059, 1060, 1061, 1228, 1269, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1291, 1293, 1329, 1516, 1517, 1521, 1522, 1523, 1524, 1525, 1526, 1527, 1528, 1582, 1583, 1587, 1588, 1589, 1590, 1591, 1592, 1593, 1598, 1599, 1600, 1614, 1618, 1685, 1686, 1687, 1688, 1689, 1690, 1691, 1692, 1693, 1694, 1695, 1696, 1697, 1698, 1699, 1752, 1753, 1754, 1756, 1757, 1758, 1759, 1760, 1761, 1762, 1763, 1797, 1798, 1830, 1831, 1832, 1833, 1834, 1835, 1836, 1837, 1838, 1839, 1840, 1841, 1842, 1843, 1844, 1845, 1846, 1847, 1848, 1886, 1887, 1888, 1893, 1894, 1895, 1896, 1897, 1898, 1899, 1900, 1901, 1902 -> 9;
            case 10, 11, 12, 13, 45, 46, 47, 99, 101, 111, 132, 133, 136, 150, 236, 286, 287, 460, 494, 495, 496, 497, 660, 687, 688, 745, 749, 747, 1191, 1192, 1193, 1194, 1195, 1196, 1197, 1198, 1199, 1200, 1201, 1331, 1608, 1609, 1610 -> 10;
            case 35, 36, 67, 84, 140, 141, 1022, 1330, 1332, 1333, 1594, 1602, 1603, 1604, 1605, 1606, 1607, 1704, 1751 -> 12;
            default -> -999;
        };
    }

}
