import es
import unittest

class TestEs(unittest.TestCase):

    def testTranspose(self):
        matrix = [[1, 3, 5], [2, 4, 6]]
        self.assertEqual(es.transpose(es.transpose(matrix)), matrix)

    def testSliceMatrix(self):
        matrix = [[1, 3, 5], [2, 4, 6]]
        left, right = es.sliceMatrix(matrix, 2)
        self.assertEqual(left, [[1, 3], [2, 4]])
        self.assertEqual(right, [[5], [6]])

    def testCheckRange(self):
        range = [3, 6]
        self.assertTrue(es.checkRange(3, *range))
        self.assertTrue(es.checkRange(6, *range))
        self.assertTrue(es.checkRange(4, *range))
        self.assertFalse(es.checkRange(8, *range))


if __name__ == '__main__':
    unittest.main()