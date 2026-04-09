/**
 * LeetCode 101：对称二叉树（递归判断左右子树是否互为镜像）
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class SolutionLC101 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    /**
     * 以 p、q 为根的子树是否互为镜像：根值相等，且 p 左与 q 右镜像、p 右与 q 左镜像。
     */
    private boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }

    public static void main(String[] args) {
        SolutionLC101 solution = new SolutionLC101();

        // [1,2,2,3,4,4,3] 对称
        TreeNode sym = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        System.out.println("对称树: " + solution.isSymmetric(sym));

        // [1,2,2,null,3,null,3] 不对称
        TreeNode asym = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(3)),
                new TreeNode(2, null, new TreeNode(3)));
        System.out.println("非对称树: " + solution.isSymmetric(asym));
    }
}
