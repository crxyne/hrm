package org.crayne.hrm.api.level.data.object.type.decoration;

import org.crayne.hrm.api.level.data.color.ColorHSBModifier;
import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("unused")
public class ColorableObject extends LevelObject {

    private int mainColorChannelIndex, secondColorChannelIndex;
    private boolean mainColorHSBEnabled, secondColorHSBEnabled;

    @Nullable
    private ColorHSBModifier mainColorHSB, secondColorHSB;

    public ColorableObject(final int objectID, final float positionX, final float positionY, final int mainColorChannelIndex,
                           final int secondColorChannelIndex, @Nullable final ColorHSBModifier mainColorHSB,
                           @Nullable final ColorHSBModifier secondColorHSB) {
        super(objectID, positionX, positionY);
        this.mainColorChannelIndex   = mainColorChannelIndex;
        this.secondColorChannelIndex = secondColorChannelIndex;
        this.mainColorHSBEnabled     = mainColorHSB != null;
        this.secondColorHSBEnabled   = secondColorHSB != null;
        this.mainColorHSB            = mainColorHSB;
        this.secondColorHSB          = secondColorHSB;
    }

    public ColorableObject(@NotNull final LevelObject levelObject, final int mainColorChannelIndex, final int secondColorChannelIndex) {
        super(levelObject);
        this.mainColorChannelIndex   = mainColorChannelIndex;
        this.secondColorChannelIndex = secondColorChannelIndex;
    }


    public ColorableObject(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
    }

    public ColorableObject(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public ColorableObject(@NotNull final Properties objectProperties) {
        super(objectProperties);

        this.mainColorChannelIndex   = objectProperties.integerProperty(LevelObjectProperty.MAIN_COLOR_ID);
        this.secondColorChannelIndex = objectProperties.integerProperty(LevelObjectProperty.SECOND_COLOR_ID);

        this.mainColorHSBEnabled     = objectProperties.booleanProperty(LevelObjectProperty.MAIN_COLOR_HSB_CHECKED);
        this.secondColorHSBEnabled   = objectProperties.booleanProperty(LevelObjectProperty.SECOND_COLOR_HSB_CHECKED);
        this.mainColorHSB            = objectProperties.hsbModifierProperty(LevelObjectProperty.MAIN_COLOR_HSB);
        this.secondColorHSB          = objectProperties.hsbModifierProperty(LevelObjectProperty.SECOND_COLOR_HSB);
    }


    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(
            1, 2, 3, 4, 5, 6, 7, 8, 15, 16, 17, 39, 62, 63, 64, 65, 66, 68, 69,
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
            1016, 1017, 1018, 1050, 1051, 1052, 1099, 1100, 1101, 1102, 1103, 1104, 1105,
            1106, 1107, 1108,
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
            1898, 1899, 1900, 1901, 1902
    );

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putIntProperty(LevelObjectProperty.MAIN_COLOR_ID, mainColorChannelIndex);
        properties.putIntProperty(LevelObjectProperty.SECOND_COLOR_ID, secondColorChannelIndex);
        properties.putBooleanProperty(LevelObjectProperty.MAIN_COLOR_HSB_CHECKED, mainColorHSBEnabled);
        properties.putBooleanProperty(LevelObjectProperty.SECOND_COLOR_HSB_CHECKED, secondColorHSBEnabled);
        properties.putHSBModifierProperty(LevelObjectProperty.MAIN_COLOR_HSB, mainColorHSB);
        properties.putHSBModifierProperty(LevelObjectProperty.SECOND_COLOR_HSB, secondColorHSB);

        return properties;
    }

    public int mainColorChannelIndex() {
        return mainColorChannelIndex;
    }

    public void mainColorChannelIndex(final int mainColorChannelIndex) {
        this.mainColorChannelIndex = mainColorChannelIndex;
    }

    public int secondColorChannelIndex() {
        return secondColorChannelIndex;
    }

    public void secondColorChannelIndex(final int secondColorChannelIndex) {
        this.secondColorChannelIndex = secondColorChannelIndex;
    }

    public boolean mainColorHSBEnabled() {
        return mainColorHSBEnabled;
    }

    public void mainColorHSBEnabled(final boolean mainColorHSBEnabled) {
        this.mainColorHSBEnabled = mainColorHSBEnabled;
    }

    public boolean secondColorHSBEnabled() {
        return secondColorHSBEnabled;
    }

    public void secondColorHSBEnabled(final boolean secondColorHSBEnabled) {
        this.secondColorHSBEnabled = secondColorHSBEnabled;
    }

    @NotNull
    public Optional<ColorHSBModifier> mainColorHSB() {
        return Optional.ofNullable(mainColorHSB);
    }

    public void mainColorHSB(@Nullable final ColorHSBModifier mainColorHSB) {
        this.mainColorHSB = mainColorHSB;
    }

    @NotNull
    public Optional<ColorHSBModifier> secondColorHSB() {
        return Optional.ofNullable(secondColorHSB);
    }

    public void secondColorHSB(@Nullable final ColorHSBModifier secondColorHSB) {
        this.secondColorHSB = secondColorHSB;
    }

    @NotNull
    public String toString() {
        return "ColorableObject{" +
                "mainColorChannelIndex=" + mainColorChannelIndex +
                ", secondColorChannelIndex=" + secondColorChannelIndex +
                ", mainColorHSBEnabled=" + mainColorHSBEnabled +
                ", secondColorHSBEnabled=" + secondColorHSBEnabled +
                ", mainColorHSB=" + mainColorHSB +
                ", secondColorHSB=" + secondColorHSB +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final ColorableObject that = (ColorableObject) o;

        if (mainColorChannelIndex != that.mainColorChannelIndex) return false;
        if (secondColorChannelIndex != that.secondColorChannelIndex) return false;
        if (mainColorHSBEnabled != that.mainColorHSBEnabled) return false;
        if (secondColorHSBEnabled != that.secondColorHSBEnabled) return false;
        if (!Objects.equals(mainColorHSB, that.mainColorHSB)) return false;
        return Objects.equals(secondColorHSB, that.secondColorHSB);
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + mainColorChannelIndex;
        result = 31 * result + secondColorChannelIndex;
        result = 31 * result + (mainColorHSBEnabled ? 1 : 0);
        result = 31 * result + (secondColorHSBEnabled ? 1 : 0);
        result = 31 * result + (mainColorHSB != null ? mainColorHSB.hashCode() : 0);
        result = 31 * result + (secondColorHSB != null ? secondColorHSB.hashCode() : 0);
        return result;
    }
}
