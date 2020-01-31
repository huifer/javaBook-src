
CREATE TABLE `repo_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `pushed_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `forks` int(11) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `watchers` int(11) DEFAULT NULL,
  `clone_url` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `homepage` varchar(100) DEFAULT NULL,
  `git_url` varchar(100) DEFAULT NULL,
  `html_url` varchar(100) DEFAULT NULL,
  `language` varchar(100) DEFAULT NULL,
  `master_branch` varchar(100) DEFAULT NULL,
  `mirror_url` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `ssh_url` varchar(100) DEFAULT NULL,
  `svn_url` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci